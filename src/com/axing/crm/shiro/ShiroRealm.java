package com.axing.crm.shiro;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axing.crm.dao.UserMapper;
import com.axing.crm.entity.Authority;
import com.axing.crm.entity.User;

@Component
public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 认证的方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		String username = token.getUsername();
		User user = userMapper.getByName(username);
		
		if(user == null){
			throw new UnknownAccountException("用户名[" + username + "]不存在.");
		}
		if(user.getEnabled() != 1){
			throw new LockedAccountException("用户名[" + username + "]被锁定.");
		}
		
		Object principal = user;
		String hashedCredentials = user.getPassword();
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
		String realmName = getName();
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, hashedCredentials, 
				credentialsSalt, realmName);
		
		return info;
	}

	@PostConstruct//初始化bean进行的操作
	public void initCredentialsMatcher(){
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName("MD5");
		credentialsMatcher.setHashIterations(1024);
		setCredentialsMatcher(credentialsMatcher);
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		User user = (User) principalCollection.getPrimaryPrincipal();
		Set<String> roles = new HashSet<>();
		
		for(Authority authority: user.getRole().getAuthorities()){
			roles.add(authority.getName());
		}
		
		System.out.println("[ROLES]: " + roles);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(roles);
		
		return info;
		
	}
	
	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";
		String credentials = "123456";
		ByteSource salt = ByteSource.Util.bytes("e2b87e6eced06509".getBytes());
		int hashIterations = 1024;
		
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result);
		
	}

}

