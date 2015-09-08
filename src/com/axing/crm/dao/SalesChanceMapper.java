package com.axing.crm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.axing.crm.entity.SalesChance;

public interface SalesChanceMapper {
	
	void updateStatus4(Long id);
	
	void updateStatus3(Long id);
	
	// 总计录数
	long selectTotalNumber(Map<String, Object> params);
	
	// 页面信息记录
	List<SalesChance> selectContent(Map<String , Object> params);

	// 新建销售计划
	void seveOrUpdate(SalesChance salesChance);

	// 获取一个 SalesChance 对象
	SalesChance get(Integer id);

	// 删除一条数据 有类型问题，在 java 接口类中时 int 类型， 在 .xml 文件中是 Long 类型
	//@Delete("delete from sales_chances where id=#{id}")
	void delete(@Param("id") Long id);
}
