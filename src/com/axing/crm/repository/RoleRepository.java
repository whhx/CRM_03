package com.axing.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.axing.crm.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>,
		JpaSpecificationExecutor<Role> {

}
