package com.axing.crm.service.jpa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axing.crm.entity.Role;
import com.axing.crm.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public void save(Role role){
		roleRepository.save(role);
	}

}
