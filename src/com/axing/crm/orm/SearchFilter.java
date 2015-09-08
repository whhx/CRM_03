package com.axing.crm.orm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class SearchFilter {

	public enum Operator {
		LIKE, EQ, GT, GE, LT, LE, ISNULL, NOTNULL,GTE,LTE;
	}

	// 属性名
	private String propertyName;
	// 查询方式
	private Operator operator;
	// 参数
	private Object propertyValue;

	public String getPropertyName() {
		return propertyName;
	}

	public Operator getOperator() {
		return operator;
	}

	public Object getPropertyValue() {
		return propertyValue;
	}

	public SearchFilter(String propertyName, Operator operator,
			Object propertyValue) {
		super();
		this.propertyName = propertyName;
		this.operator = operator;
		this.propertyValue = propertyValue;
	}

	public static List<SearchFilter> parseParamToFilters(Map<String, Object> params){
		List<SearchFilter> filters = new ArrayList<>();
		
		if(params == null || params.size() ==0){
			return filters;
		}
		
		// 目标 LIKE_contact	ss 拆分成对应三部分属性
		for(Map.Entry<String, Object> entry : params.entrySet()){
			String key = entry.getKey();
			
			Object propertyValue = entry.getValue();
			if(propertyValue == null || "".equals(propertyValue.toString().trim())){
				continue;
			}
			
			String operatorCode = StringUtils.substringBefore(key, "_");

			Operator operator = Enum.valueOf(Operator.class, operatorCode);
			String propertyName = StringUtils.substringAfter(key, "_");
			
			SearchFilter filter = new SearchFilter(propertyName, operator, propertyValue);
			filters.add(filter);
		}
		return filters;
	}
}
