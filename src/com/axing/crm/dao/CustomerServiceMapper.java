package com.axing.crm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import com.axing.crm.entity.Customer;
import com.axing.crm.entity.CustomerService;
import com.axing.crm.entity.Dict;

@Service
public interface CustomerServiceMapper {

	
	@Select("select * from dicts where type = #{type}")
	List<Dict> selectDict(@Param("type") String type);

	@Select("select * from customers")
	List<Customer> getCustomer();

	/*@Insert("insert into customer_services (id,service_type,service_title,"
			+ "customer_id,created_id,service_state,service_request,create_date) "
			+ "values (crm_seq.nextval,#{serviceType},#{serviceTitle},#{customer.id},"
			+ "#{createdId},#{serviceState},#{serviceRequest},#{createDate}")*/
	void save(CustomerService customerService);

	
	long selectTotalNumber(Map<String, Object> params);

	
	List<CustomerService> selectContent(Map<String, Object> params);

	CustomerService get(Integer id);

	void update(CustomerService cs);

	void update2(CustomerService customerService);

	void update3(CustomerService customerService);

	


}
