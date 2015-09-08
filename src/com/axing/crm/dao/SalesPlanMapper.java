package com.axing.crm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.axing.crm.entity.SalesChance;
import com.axing.crm.entity.SalesPlan;

public interface SalesPlanMapper {
	
	
	// 总计录数
	long selectTotalNumber(Map<String, Object> params);
	
	// 页面信息记录
	List<SalesChance> selectContent(Map<String , Object> params);

	//获取一个SalesPlan对象
	SalesPlan get(Integer id);
	
	
	
	SalesChance getChanceById(@Param("chanceId") Integer id);

	@SelectKey(before=true, keyProperty="id", resultType=Long.class, 
			statement="SELECT crm_seq.nextval FROM dual")
	@Insert("INSERT INTO sales_plan(id, plan_date, todo, chance_id) "
			+ "VALUES(#{id}, #{date}, #{todo}, #{chance.id})")
	void save(SalesPlan plan);

	@Update("update sales_plan set todo = #{todo} where id = #{id}")
	void update(SalesPlan SalexPlan);

	@Delete("delete from sales_plan where id = #{id}")
	void delete(Long id);


	@Update("update sales_plan set plan_result = #{result} where id = #{id}")
	void updateResult(SalesPlan plan);
	

}
