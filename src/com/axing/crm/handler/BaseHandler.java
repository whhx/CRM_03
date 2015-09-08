package com.axing.crm.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.axing.crm.service.mybatis.SalesChanceService;
import com.axing.crm.service.mybatis.SalesPlanService;
import com.axing.crm.service.mybatis.UserService;

public class BaseHandler {
	
	@Autowired
	protected ResourceBundleMessageSource messageSource;

	@Autowired
	protected UserService userService;
	
	@Autowired
	protected SalesChanceService salesChanceService;

	@Autowired
	protected SalesPlanService salesPlanService;
}

