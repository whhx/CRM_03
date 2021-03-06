package com.axing.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.axing.crm.entity.User;

public interface UserMapper {

	/*@Select(value="select u.id, u.enabled, u.name, u.password, u.role_id as \"role.id\", u.salt, r.name as \"role.name\" "
			+ " from users u "
			+ " left outer join roles r "
			+ " on u.role_id = r.id "
			+ " where u.name = #{name}")
	User getByName(@Param("name") String name);

	@Select("select id, enabled, name, password, role_id, salt from users")
	List<User> getAll();*/
	
	@Select("SELECT id, enabled, name, password, role_id, salt FROM users")
	List<User> getAll();
	
	/*@Select("SELECT u.id, u.name, password, u.enabled, u.salt, role_id as \"role.id\", r.name as \"role.name\" "
			+ " FROM users u "
			+ " LEFT OUTER JOIN roles r "
			+ " ON u.role_id = r.id "
			+ " WHERE u.name = #{name}")*/
	User getByName(@Param("name") String name);
	
	
}
