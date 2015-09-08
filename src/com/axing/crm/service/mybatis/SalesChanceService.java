package com.axing.crm.service.mybatis;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axing.crm.dao.ContactMapper;
import com.axing.crm.dao.CustomerMapper;
import com.axing.crm.dao.SalesChanceMapper;
import com.axing.crm.entity.Contact;
import com.axing.crm.entity.Customer;
import com.axing.crm.entity.SalesChance;
import com.axing.crm.orm.Page;
import com.axing.crm.utils.MyBatisUtils;

@Service
public class SalesChanceService {

	@Autowired
	private SalesChanceMapper salesChanceMapper;
	
	@Autowired
	private ContactMapper contactMapper;
	
	@Autowired
	private CustomerMapper customerMapper;
	
	@Transactional(readOnly=true)
	public SalesChance detail(Integer id) {
		return salesChanceMapper.get(id);
		
	}
	
	@Transactional(readOnly=true)
	public void stop(Integer id) {
		//SalesChance chance = salesChanceMapper.get(id);
		// status 修改为 4
		//chance.setStatus(4);
		salesChanceMapper.updateStatus4((long)id);
		
	}
	
	@Transactional(readOnly=true)
	public void finish(Integer id) {
		//1.获取 SalesChance，设置 Status 状态为 3
		SalesChance chance = salesChanceMapper.get(id);
		//chance.setStatus(3);
		salesChanceMapper.updateStatus3((long)id);

		//2. 插入一条 Customer  name，no（随机字符串） 和 state（正常）.
		Customer customer = new Customer();
		customer.setName(chance.getCustName());
		customer.setNo(UUID.randomUUID().toString());
		customer.setState("正常");
		customerMapper.saveForSuccess(customer);
		
		//3. 插入一条 Contact,  name、tel、customer_id
		Contact contact = new Contact();
		contact.setName(chance.getContact());
		contact.setTel(chance.getContactTel());
		
		contact.setCustomer(customer);
		contactMapper.saveForSuccess(contact);
		
		
	}
	
	
	@Transactional(readOnly=false)
	public void delete(Long id) {
		salesChanceMapper.delete(id);
	}
	
	@Transactional(readOnly=true)
	public SalesChance get(Integer id) {
		return salesChanceMapper.get(id);
	}
	
	@Transactional(readOnly=false)
	public void seveOrUpdate(SalesChance salesChance) {
		if(salesChance.getId() == null){
			salesChance.setStatus(1);
		}
		salesChanceMapper.seveOrUpdate(salesChance);
	}
	
	// LIKE_contact	ss   
	@Transactional(readOnly=true)
	public Page<SalesChance> getPage(int pageNo, int pageSize, Map<String , Object> params){
		// contact	%ss%
		params = MyBatisUtils.getParametersStartingWith(params);
		
		// 设置没有指派的客户
		params.put("status", 1);
		
		Page<SalesChance> page = new Page<>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		
		long totalNumber = salesChanceMapper.selectTotalNumber(params);
		page.setTotal(totalNumber);
		
		int fromIndex = (page.getNumber() - 1) * page.getSize();
		int endIndex = fromIndex + page.getSize();
		
		params.put("fromIndex", fromIndex);
		params.put("endIndex", endIndex);
		
		List<SalesChance> content = salesChanceMapper.selectContent(params);
		page.setContent(content);
		
		return page;
	}

}
