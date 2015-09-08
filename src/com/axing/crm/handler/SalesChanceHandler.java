package com.axing.crm.handler;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.axing.crm.entity.SalesChance;
import com.axing.crm.entity.User;
import com.axing.crm.orm.Page;
import com.axing.crm.web.Servlets;

@RequestMapping("/chance")
@Controller
public class SalesChanceHandler extends BaseHandler{
	

	
	
	@RequestMapping(value="/stop")
	public String stop(@RequestParam("id") Integer id, RedirectAttributes attributes) {
		salesChanceService.stop(id);
		attributes.addFlashAttribute("message", "终止开发成功");
		return "redirect:/plan/chance/list";
	}
	
	@RequestMapping(value="/detail")
	public String detail(@RequestParam("id") Integer id, Map<String, Object> map) {
		
		map.put("chance", salesChanceService.detail(id));
		return "plan/detail";
	}
	
	@RequestMapping(value="/finish")
	public String finish(@RequestParam("id") Integer id,RedirectAttributes attributes) {
		
		salesChanceService.finish(id);
		attributes.addFlashAttribute("message", "客户开发成功!");
		return "redirect:/plan/chance/list";
	}
	
	
	
	@RequestMapping(value="/dispatch", method=RequestMethod.PUT)
	public String dispatch(SalesChance salesChance){
		
		//salesChance.setStatus(2);
		salesChanceService.seveOrUpdate(salesChance);
		
		return "redirect:/chance/list";
	}
	
	@RequestMapping(value="/dispatch/{id}", method=RequestMethod.GET)
	public String dispatch(@PathVariable("id") String idStr, Map<String, Object> map){
		int id = -1;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {}
		SalesChance chance = salesChanceService.get(id);
		if(chance == null){
			// 转向指定页面
		}
		map.put("chance", chance);
		List<User> users = userService.getAll();
		map.put("users", users);
		return "/chance/dispatch";
	}

	//=========================
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id, HttpServletRequest request){
		
		salesChanceService.delete(id);
		
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		String queryString = Servlets.encodeParameterStringWithPrefix(params, "search_");
		return "redirect:/chance/list?" + queryString;
	}
	
	//=========================
	// 问题使用 @ModelAttribute 所在的里能不能有父类
	@ModelAttribute
	public void getSalesChance(@RequestParam(value="id", required=false) Integer id,
			Map<String, Object> map){
		if(id != null){
			map.put("salesChance", salesChanceService.get(id));
		}
	}
	
	@RequestMapping(value="/create", method=RequestMethod.PUT)
	public String update(SalesChance salesChance){
		salesChanceService.seveOrUpdate(salesChance);
		return "redirect:/chance/list";
	}
	
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String update(@PathVariable("id") String idStr, Map<String, Object> map){
		Integer id = -1;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {}
		SalesChance salesChance = salesChanceService.get(id);
		if(salesChance == null){
			// 转向一个专门页面
		}
		map.put("chance", salesChance);
		return "/chance/input";
	}
	
	//=========================
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(SalesChance salesChance, RedirectAttributes redirectAttributes){
		
		salesChanceService.seveOrUpdate(salesChance);

		redirectAttributes.addFlashAttribute("message", "创建成功");
		
		return "redirect:/chance/list";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String create(Map<String, Object> map){
		SalesChance chance = new SalesChance();
		map.put("chance", chance);
		return "/chance/input";
	}
	
	//=========================
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false,defaultValue="1") String pageNoStr,
			HttpServletRequest request){
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		// 获取指定请求参数，有截取功能
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, "search_");
		
		// 把 params 转为一个查询的字符串，再传回到页面
		String queryString = Servlets.encodeParameterStringWithPrefix(params, "search_");
		
		Page<SalesChance> page = salesChanceService.getPage(pageNo, 5, params);
		
		request.setAttribute("page", page);
		request.setAttribute("queryString", queryString);
		return "/chance/list";
	}
}
