package com.axing.crm.service.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axing.crm.dao.SalesPlanMapper;
import com.axing.crm.entity.SalesChance;
import com.axing.crm.entity.SalesPlan;
import com.axing.crm.entity.User;
import com.axing.crm.orm.Page;
import com.axing.crm.utils.MyBatisUtils;

@Service
public class SalesPlanService {

	@Autowired
	private SalesPlanMapper salesPlanMapper;
	
	
	@Transactional(readOnly=true)
	public Page<SalesChance> getPage(int pageNo, int pageSize, Map<String , Object> params,User user){
		// contact	%ss%
		params = MyBatisUtils.getParametersStartingWith(params);
		
		// 设置指派的客户
		params.put("status", 2);
		params.put("id",user.getId());
		
		
		
		Page<SalesChance> page = new Page<>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		
		long totalNumber = salesPlanMapper.selectTotalNumber(params);
		page.setTotal(totalNumber);
		
		int fromIndex = (page.getNumber() - 1) * page.getSize();
		int endIndex = fromIndex + page.getSize();
		
		params.put("fromIndex", fromIndex);
		params.put("endIndex", endIndex);
		
		List<SalesChance> content = salesPlanMapper.selectContent(params);
		page.setContent(content);
		
		return page;
	}

	@Transactional
	public SalesPlan get(Integer id) {
		return salesPlanMapper.get(id);
	}
	
	
	
	@Transactional
	public Long save(SalesPlan plan){
		salesPlanMapper.save(plan);
		return plan.getId();
	}
	
	@Transactional(readOnly=true)
	public SalesChance getByChanceId(Integer chanceId){
		return salesPlanMapper.getChanceById(chanceId);
	}

	@Transactional
	public Long update(SalesPlan salesPlan) {
		salesPlanMapper.update(salesPlan);
		return salesPlan.getId();
	}
	

	@Transactional
	public Long delete(Long id) {
		salesPlanMapper.delete(id);
		return id;
	}

	@Transactional
	public void updateResult(SalesPlan plan) {
		salesPlanMapper.updateResult(plan);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	/*@Transactional(readOnly=false)
	public void save(SalesPlan salesPlan){
		salesPlanMapper.save(salesPlan);
	}
	
	public void seveOrUpdate(SalesPlan salesPlan) {
		salesPlanMapper.seveOrUpdate(salesPlan);
	}*/
}
