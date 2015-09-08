package com.axing.crm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Update;

import com.axing.crm.entity.CustomerDrain;

public interface CustomerDrainMapper {
	@Update("{call drain_procedure2()}")
	void callProcedure();

	long selectTotalNumber(Map<String, Object> params);

	List<CustomerDrain> selectContent(Map<String, Object> params);

}
