package Test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.axing.crm.entity.Role;
import com.axing.crm.service.jpa.RoleService;
import com.axing.crm.service.mybatis.SalesPlanService;

public class CrmTest {
	
	private ApplicationContext ctx = null;
	private SalesPlanService salesPlanService;
	private RoleService roleService;
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		salesPlanService = ctx.getBean(SalesPlanService.class);
		roleService = ctx.getBean(RoleService.class);
	}
	

	@Test
	public void testSpringData() {
		Role role = new Role();
		role.setDescription("ABC");
		role.setEnabled(true);
		role.setName("BB");
		
		roleService.save(role);
		
	}

}
