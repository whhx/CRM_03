package com.axing.crm.service.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axing.crm.dao.CustomerServiceMapper;
import com.axing.crm.entity.Customer;
import com.axing.crm.entity.CustomerService;
import com.axing.crm.entity.Dict;
import com.axing.crm.orm.Page;
import com.axing.crm.utils.MyBatisUtils;


@Service
public class CustomerServiceService {
	
	@Autowired
	private CustomerServiceMapper customerServiceMapper;

	@Transactional(readOnly=true)
	public List<Dict> selectDict(String type) {
		
		return customerServiceMapper.selectDict(type);
	}

	@Transactional(readOnly=true)
	public List<Customer> getCustomer() {
		
		return customerServiceMapper.getCustomer();
	}

	@Transactional
	public void save(CustomerService customerService) {
		customerServiceMapper.save(customerService);
		
	}

	@Transactional(readOnly=true)
	public Page<CustomerService> getPage(int pageNo, int pageSize,
			Map<String, Object> params,String serviceState) {
		
		params = MyBatisUtils.getParametersStartingWith(params);
		
		params.put("serviceState", serviceState);
		
		Page<CustomerService> page = new Page<>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		
		long totalNumber = customerServiceMapper.selectTotalNumber(params);
		page.setTotal(totalNumber);
		
		int fromIndex = (page.getNumber() - 1) * page.getSize();
		int endIndex = fromIndex + page.getSize();
		
		params.put("fromIndex", fromIndex);
		params.put("endIndex", endIndex);
		
		List<CustomerService> content = customerServiceMapper.selectContent(params);
		page.setContent(content);
		
		return page;
	}

	@Transactional
	public CustomerService get(Integer id) {
		CustomerService cs = customerServiceMapper.get(id);
		return cs;
	}

	@Transactional
	public void update(CustomerService cs) {
		customerServiceMapper.update(cs);
	}

	public void update2(CustomerService customerService) {
		customerServiceMapper.update2(customerService);
		
	}

	public void update3(CustomerService customerService) {
		customerServiceMapper.update3(customerService);
		
	}


}
