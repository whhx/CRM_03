package com.axing.crm.web;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Servlets {

	// 重新拼接请求参数字符窜，LIKE_contact	ss
	public static String encodeParameterStringWithPrefix(Map<String, Object> params, String prefix){

		if((params == null) || (params.size() == 0)){
			return "";
		}
		if(prefix == null){
			prefix = "";
		}
		
		StringBuilder queryStringBuilder = new StringBuilder();
		Set<Entry<String,Object>> entrySet = params.entrySet();
		
		for (Entry<String, Object> entry : entrySet) {
			queryStringBuilder.append(prefix).append(entry.getKey())
				.append("=").append(entry.getValue()).append("&");
		}
		return queryStringBuilder.substring(0, queryStringBuilder.length()-1);
	}
}
