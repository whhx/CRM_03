package com.axing.crm.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.axing.crm.orm.SearchFilter;

public class MyBatisUtils {

	// 目标 LIKE_contact	ss  ---> contact	%ss%
	public static Map<String, Object> getParametersStartingWith(Map<String , Object> params){
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<SearchFilter> filters = SearchFilter.parseParamToFilters(params);
		
		if(filters == null || filters.size() == 0){
			return result;
		}
		
		for (SearchFilter filter : filters) {
			if("".equals(filter.getPropertyValue())){
				result.put(filter.getPropertyName(), null);
				continue;
			}
			switch (filter.getOperator()) {
			case LIKE:
				result.put(filter.getPropertyName(),"%" + filter.getPropertyValue() + "%");
				break;
			case EQ:
				result.put(filter.getPropertyName(), filter.getPropertyValue());
			default:
				break;
			}
		}
		return result;
	}
}
