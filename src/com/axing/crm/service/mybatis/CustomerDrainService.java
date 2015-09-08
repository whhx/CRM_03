package com.axing.crm.service.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axing.crm.dao.CustomerDrainMapper;
import com.axing.crm.entity.CustomerDrain;
import com.axing.crm.orm.Page;
import com.axing.crm.utils.MyBatisUtils;

@Service
public class CustomerDrainService {
	@Autowired
	private CustomerDrainMapper customerDrainMapper;
	
	@Transactional
	public void save(){
		System.out.println("[CustomerDrainService#save]");
		customerDrainMapper.callProcedure();
	}
	
	public long gselectTotalNumber(Map<String, Object> params){
		return customerDrainMapper.selectTotalNumber(params);
	}

	@Transactional
	public Page<CustomerDrain> getPage(int pageNo, int pageSize,
			Map<String, Object> params) {
		params = MyBatisUtils.getParametersStartingWith(params);
		
		Page<CustomerDrain> page = new Page<>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		
		long totalNumber = customerDrainMapper.selectTotalNumber(params);
		page.setTotal(totalNumber);
		
		int fromIndex = (page.getNumber() - 1) * page.getSize();
		int endIndex = fromIndex + page.getSize();
		
		params.put("fromIndex", fromIndex);
		params.put("endIndex", endIndex);
		
		List<CustomerDrain> content = customerDrainMapper.selectContent(params);
		page.setContent(content);
		
		return page;
	}


}
