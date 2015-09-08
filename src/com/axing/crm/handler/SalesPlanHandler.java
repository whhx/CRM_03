package com.axing.crm.handler;

import java.text.ParseException;
import java.util.Map;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.axing.crm.entity.SalesChance;
import com.axing.crm.entity.SalesPlan;
import com.axing.crm.entity.User;
import com.axing.crm.orm.Page;
import com.axing.crm.web.Servlets;


@RequestMapping("/plan/chance")
@Controller
public class SalesPlanHandler extends BaseHandler {
	
	//执行保存结果
	@ResponseBody
	@RequestMapping(value={"/execute"},method=RequestMethod.PUT)
	public String execute(SalesPlan plan){
		salesPlanService.updateResult(plan);
		return "1";
	}
	
	@RequestMapping(value={"/execution"})
	public String execution(@RequestParam("id") Integer id,Map<String, Object> map){
				
		SalesChance salesChance = salesChanceService.get(id);
		map.put("chance", salesChance);
		
		return "/plan/exec";
	}
	
	@ResponseBody
	@RequestMapping(value={"/delete-ajax"},method=RequestMethod.DELETE)
	public String delete(@RequestParam("id") Long id){
		Long ids = salesPlanService.delete(id);
		
		return ""+ids;
	}
	
	@ResponseBody
	@RequestMapping(value={"/make-ajax"})
	public String update(SalesPlan salesPlan){
		Long id = salesPlanService.update(salesPlan);
		
		return ""+id;
	}
	
	
	@ResponseBody
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String create(SalesPlan plan) throws ParseException{
		Long id = salesPlanService.save(plan);
		return "" + id;
	}
	
	@RequestMapping("/create")
	public String create(@RequestParam("chanceId") Integer chanceId, 
			Map<String, Object> map){
		SalesChance salesChance = salesPlanService.getByChanceId(chanceId);
		map.put("chance", salesChance);
		return "plan/make";
	}
	
	/*@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(SalesPlan salesPlan, RedirectAttributes redirectAttributes){
		
		//salesPlanService.seveOrUpdate(salesPlan);
		salesPlanService.save(salesPlan);
		
		redirectAttributes.addFlashAttribute("message", "创建成功");
		
		return "redirect:/plan/chance/input";
	}*/
	/*
	@RequestMapping(value="/create/{id}",method=RequestMethod.GET)
	public String create(@PathVariable("id") String idStr, Map<String, Object> map){
		Integer id = -1;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {}
		
		
		SalesChance salesPlan = salesChanceService.get(id);
		if(salesPlan == null){
			// 转向一个专门页面
		}
		map.put("chance", salesPlan);
		System.out.println(salesPlan);
		//map.put("des", salesPlan.getDesignee().getName());
		return "/plan/make";
	}
	*/
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
		User user = (User) session.getAttribute("user");
		Page<SalesChance> page = salesPlanService.getPage(pageNo, 5, params,user);
		
		request.setAttribute("page", page);
		request.setAttribute("queryString", queryString);
		return "/plan/list";
	}

	
}
