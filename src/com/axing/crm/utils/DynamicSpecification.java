package com.axing.crm.utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.axing.crm.orm.SearchFilter;
import com.axing.crm.orm.SearchFilter.Operator;


public class DynamicSpecification {

	public static <T> Specification<T> buildSpecification(
			final List<SearchFilter> filters) {
		//Specification: SpringData 封装 JPA Criteria 查询条件的对象. 
		//实际使用的是其抽象方法 toPredicate 的返回值. 
		Specification<T> specification = new Specification<T>() {
			/**
			 * Predicate: JPA Criteria 查询的查询条件
			 * Root: 要查询的根对象. 即查询的对象. 可以导航到对应的属性上. 
			 * CriteriaBuilder: JPA Criteria 查询的工厂类. 用于创建 JPA Criteria 查询需要的对象. 例如用于创建 
			 * Predicate 对象. 
			 */
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
					CriteriaBuilder builder) {
				Predicate predicate = null;
				if(filters == null || filters.size() == 0){
					return builder.conjunction();
				}
				
				List<Predicate> predicates = new ArrayList<>();
				
				for(SearchFilter filter: filters){
					//传入的属性名有可能是级联属性! 例如传入的是 manager.name
					String propertyNames = filter.getPropertyName();
					String [] names = propertyNames.split("\\.");
					Path path = root.get(names[0]);
					if(names.length > 1){
						for(int i = 1; i < names.length; i++){
							path = path.get(names[i]);
						}	
					}
					
					Operator operator = filter.getOperator();
					Object propertyValue = filter.getPropertyValue();
					
					Predicate pre = null;
					switch(operator){
					case LIKE:
						pre = builder.like(path, "%" + propertyValue + "%");
						break;
					}
					
					if(pre != null){
						predicates.add(pre);
					}
				}
				
				return builder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
		
		return specification;
	}

}

