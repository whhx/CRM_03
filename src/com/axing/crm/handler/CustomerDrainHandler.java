package com.axing.crm.handler;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.axing.crm.entity.CustomerDrain;
import com.axing.crm.orm.Page;
import com.axing.crm.service.mybatis.CustomerDrainService;
import com.axing.crm.web.Servlets;

@RequestMapping("/drain")
@Controller
public class CustomerDrainHandler extends BaseHandler {
	
	@Autowired
	private CustomerDrainService customerDrainService;
	
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false,defaultValue="1") String pageNoStr,
			HttpServletRequest request,HttpSession session){
		
		int pageNo = 1;
		try {
			pageNo=Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		// 获取指定请求参数，有截取功能
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
				
		// 把 params 转为一个查询的字符串，再传回到页面
		String queryString = Servlets.encodeParameterStringWithPrefix(params, "search_");
		
		Page<CustomerDrain> page = customerDrainService.getPage(pageNo, 5, params);
		request.setAttribute("page", page);
		request.setAttribute("queryString", queryString);
		return "drain/list";
	}

}
