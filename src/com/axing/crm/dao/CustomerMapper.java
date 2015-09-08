package com.axing.crm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.axing.crm.entity.Contact;
import com.axing.crm.entity.Customer;
import com.axing.crm.entity.Dict;

public interface CustomerMapper {
	
	
	void saveForSuccess(Customer customer);

	// 总计录数
	long selectTotalNumber(Map<String, Object> params);
		
	// 页面信息记录
	List<Customer> selectContent(Map<String , Object> params);

	// 地区，等级 
	@Select("select id, editable, item, type, value from dicts where type = #{type}")
	List<Dict> selectDict(@Param("type") String type);

	Customer get(int id);
	
	// 获取所有用 Contact 信息
	@Select("select co.id, memo, mobile, co.name, position, sex, co.tel, customer_id as \"c.id\" "
			+ " from contacts co "
			+ " left outer join customers c "
			+ " on co.customer_id = c.id ")
	List<Contact> getContact();

	// 更新
	void update(Customer customer);

	// 修改 Customer 状态
	void delete(Customer customer);

}
