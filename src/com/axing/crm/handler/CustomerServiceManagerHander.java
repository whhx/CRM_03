package com.axing.crm.handler;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.axing.crm.entity.Customer;
import com.axing.crm.entity.CustomerService;
import com.axing.crm.entity.Dict;
import com.axing.crm.entity.User;
import com.axing.crm.orm.Page;
import com.axing.crm.service.mybatis.CustomerServiceService;
import com.axing.crm.web.Servlets;

@RequestMapping("/service")
@Controller
public class CustomerServiceManagerHander extends BaseHandler {
	
	@Autowired
	private CustomerServiceService customerServiceService;
	
	@RequestMapping(value={"/archive"},method=RequestMethod.GET)
	public String archive(@RequestParam("id") Integer id,Map<String, Object> map){
		CustomerService service = customerServiceService.get(id);
		map.put("chance", service);
		
		return "/service/archive/archive";
	}
	
	@RequestMapping(value={"/archive/list"})
	public String archive(@RequestParam(value="page",required=false,defaultValue="1") String pageNoStr,
			HttpServletRequest request){
		
		int pageNo = 1;
		
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		// 获取指定请求参数，有截取功能
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		
		// 把 params 转为一个查询的字符串，再传回到页面
		String queryString = Servlets.encodeParameterStringWithPrefix(params, "search_");
		Page<CustomerService> page = customerServiceService.getPage(pageNo,5, params,"已归档");
		
		request.setAttribute("page", page);
		request.setAttribute("queryString", queryString);
		
		
		return "/service/archive/list";
	}
	
	@RequestMapping(value={"/feedback"},method=RequestMethod.POST)
	public String feedBack(CustomerService customerService){
		customerService.setServiceState("已归档");
		customerServiceService.update3(customerService);
		
		return "redirect:/service/feedBack/list";
	}
	
	@RequestMapping(value={"/feedback/{id}"},method=RequestMethod.GET)
	public String feedBack(@PathVariable("id") String idStr,Map<String,Object> map){
		
		Integer id = -1;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {}
		
		CustomerService service = customerServiceService.get(id);
		
		if(service == null){
			//转到特定的页面
		}
		map.put("chance", service);
		
		return "/service/feedBack/feedBack";
	}
	
	
	@RequestMapping(value={"/feedBack/list"})
	public String feedBack(@RequestParam(value="page",required=false,defaultValue="1") String pageNoStr,
			HttpServletRequest request){
		
		int pageNo = 1;
		
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		// 获取指定请求参数，有截取功能
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		
		// 把 params 转为一个查询的字符串，再传回到页面
		String queryString = Servlets.encodeParameterStringWithPrefix(params, "search_");
		Page<CustomerService> page = customerServiceService.getPage(pageNo,5, params,"已处理");
		
		request.setAttribute("page", page);
		request.setAttribute("queryString", queryString);
		
		return "/service/feedBack/list";
	}
	
	
	@RequestMapping(value={"/deal"},method=RequestMethod.POST)
	public String deal(CustomerService customerService){
		//System.out.println(customerService.getId());
		customerService.setServiceState("已处理");
		customerService.setDealDate(new Date());
		customerServiceService.update2(customerService);
		
		return "redirect:/service/deal/list";
	}
	
	@RequestMapping(value={"/deal/{id}"},method=RequestMethod.GET)
	public String deal(@PathVariable("id") String idStr,Map<String,Object> map){
		Integer id = -1;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {}
		
		CustomerService service = customerServiceService.get(id);
		
		if(service == null){
			//转到特定的页面
		}
		map.put("chance", service);
		return "/service/deal/deal";
	}
	
	
	@RequestMapping(value={"/deal/list"})
	public String dealList(@RequestParam(value="page",required=false,defaultValue="1") String pageNoStr,
			HttpServletRequest request){
		
		int pageNo = 1;
		
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		// 获取指定请求参数，有截取功能
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		
		// 把 params 转为一个查询的字符串，再传回到页面
		String queryString = Servlets.encodeParameterStringWithPrefix(params, "search_");
		Page<CustomerService> page = customerServiceService.getPage(pageNo,5, params,"已分配");
		
		request.setAttribute("page", page);
		request.setAttribute("queryString", queryString);
		
		
		return "/service/deal/list";
	}
	
	@ResponseBody
	@RequestMapping(value={"/allot"},method=RequestMethod.POST)
	public String allot(CustomerService customerService){
		Date date = new Date();
		System.out.println(date);
		customerService.setAllotDate(date);
		customerServiceService.update(customerService);
		
		return "1";
	}
	
	
	//添加
	@RequestMapping(value={"/create"},method=RequestMethod.POST)
	public String create(CustomerService customerService,RedirectAttributes redirectAttributes){
		customerServiceService.save(customerService);
		redirectAttributes.addFlashAttribute("message", "创建成功");
		return "redirect:/service/list";
	}
	
	//转到指定页面
	@RequestMapping(value={"/create"},method=RequestMethod.GET)
	public String create(Map<String,Object> map,HttpSession session){

		CustomerService customerService = new CustomerService();
		map.put("customerService", customerService);
		
		map.put("user", session.getAttribute("user"));
		
		List<Customer> customers = customerServiceService.getCustomer();
		map.put("customers", customers);
		
		List<Dict> serviceTypes = customerServiceService.selectDict("服务类型");
		map.put("serviceTypes", serviceTypes);
		
		return "/service/create";
	}
	
	
	//分页
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false,defaultValue="1") String pageNoStr,
			HttpServletRequest request,HttpSession session){
		
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		// 获取指定请求参数，有截取功能
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		
		// 把 params 转为一个查询的字符串，再传回到页面
		String queryString = Servlets.encodeParameterStringWithPrefix(params, "search_");
		Page<CustomerService> page = customerServiceService.getPage(pageNo,5, params,"新创建");
		
		List<User> users = userService.getAll();
		session.setAttribute("users", users);
		
		request.setAttribute("page", page);
		request.setAttribute("queryString", queryString);
		return "/service/allot/list";
	}
	
	
	
	
	
	
	
	
	

}
