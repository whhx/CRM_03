package com.axing.crm.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.axing.crm.entity.Dict;
import com.axing.crm.service.jpa.DictService;
import com.axing.crm.web.Servlets;



@Controller
@RequestMapping("/dict")
public class DictHandler {
	
	@Autowired
	private DictService dictService;
	
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false,defaultValue="1") int pageNo,HttpServletRequest request){
		//1.获取查询条件请求参数对相应的Map
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		
		//2.调用service方法得到Page对象
		Page<Dict> page = dictService.getPage(pageNo-1, params);
		request.setAttribute("page", page);
		
		//3.把params转为查询字符串，回传给页面
		String queryString = Servlets.encodeParameterStringWithPrefix(params, "search_");
		request.setAttribute("searchParams", queryString);
		return "dict/list";
	}
	
}
