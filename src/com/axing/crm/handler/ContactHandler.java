package com.axing.crm.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.axing.crm.entity.Contact;
import com.axing.crm.entity.Customer;
import com.axing.crm.orm.Page;
import com.axing.crm.service.mybatis.ContactService;

@RequestMapping(value="/contact")
@Controller
public class ContactHandler {

	@Autowired
	public ContactService contactService;
	
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public String delete(@RequestParam("id") Integer id, @RequestParam("customerid") Integer customerid,
			RedirectAttributes reAttributes){
		long num = contactService.getCustomerCount(customerid);
		if(num > 1){
			contactService.delete(id);
		}else{
			// 删除失败
			reAttributes.addFlashAttribute("message", "最少保留一个联系人");
		}
		
		return "redirect:/contact/list?id=" + customerid;
	}
	
	//=========================
	@RequestMapping(value="/create", method={RequestMethod.POST, RequestMethod.PUT})
	public String save(Contact contact, RedirectAttributes reAttributes){
		contactService.saveOrdUpdate(contact);
		reAttributes.addFlashAttribute("message", "保存成功");
		return "redirect:/contact/list?id=" + contact.getCustomer().getId();
	}
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String create(@RequestParam(value="customerid", required=false) Integer customerid, Map<String, Object> map,
			@RequestParam(value="id", required=false) Integer id){
		if(customerid != null){
			map.put("contact", new Contact());
			map.put("customerid", customerid);
		}
		if(id != null){
			Contact contact = contactService.getByContactId(id);
			map.put("contact", contact);
		}
		map.put("sex", getSex());
		
		return "/contact/input";
	}
	
	//=========================
	@ModelAttribute
	public void getContact(@RequestParam(value="id", required=false) Integer id, Map<String, Object> map){
		if(id != null){
			Contact contact = contactService.getByContactId(id);
			map.put("contact", contact);
		}
	}
	
	// 辅助方法
	public Map<String, String> getSex(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("男", "男");
		map.put("女", "女");
		return map;
	}
	
	//=========================
	@RequestMapping(value="/list")
	public String list(@RequestParam("id") Integer id, Map<String, Object> map,
			@RequestParam(value="page", required=false, defaultValue="1") String pageNoStr){
		
		int pageNo = 1;
		int pageSize = 5;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		Page<Contact> page = contactService.getByCostomerId(pageNo, pageSize, id);
		
		Customer customer = page.getContent().get(0).getCustomer();
		
		map.put("customer", customer);
		map.put("page", page);
		
		return "/contact/list";
	}
}
