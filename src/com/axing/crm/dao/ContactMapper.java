package com.axing.crm.dao;

import java.util.List;
import java.util.Map;

import com.axing.crm.entity.Contact;

public interface ContactMapper {
	
	
	void saveForSuccess(Contact contact);
	
	// 总计录数
	long selectTotalNumber(Map<String, Object> params);
		
	// 页面信息记录
	List<Contact> selectContent(Map<String , Object> params);

	// 保存
	void saveOrdUpdate(Contact contact);

	Contact getByContactId(Integer id);

	// 获取指向同一个 Customer 的数量
	long getCustomerCount(Integer customerid);

	// 删除 Contact
	void delete(Integer id);
	
}
