package com.axing.crm.service.jpa;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.axing.crm.entity.Dict;
import com.axing.crm.orm.SearchFilter;
import com.axing.crm.repository.DictRepository;
import com.axing.crm.utils.DynamicSpecification;

@Service
public class DictService {

	
	@Autowired
	private DictRepository dictRepository;
	
	public Page<Dict> getPage(int pageNo,Map<String,Object> params){
		//1. 把 params 转为 SearchFilter 的集合
		List<SearchFilter> filters = SearchFilter.parseParamToFilters(params);
		//2. 把 filters 转为 Specification 对象
		Specification<Dict> specification = DynamicSpecification.buildSpecification(filters);
		//3. 获取 Page 对象
		PageRequest pageable = new PageRequest(pageNo, 5);
		return dictRepository.findAll(specification, pageable);
		
	}
}
