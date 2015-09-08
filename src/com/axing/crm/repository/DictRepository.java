package com.axing.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.axing.crm.entity.Dict;

public interface DictRepository extends JpaRepository<Dict, Long>,
	JpaSpecificationExecutor<Dict> {

}
