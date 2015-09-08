package Test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.axing.crm.entity.Role;
import com.axing.crm.service.jpa.RoleService;
import com.axing.crm.service.mybatis.CustomerDrainService;
import com.axing.crm.service.mybatis.SalesPlanService;

public class CustomerDrainTest {
	
	private ApplicationContext ctx = null;
	private CustomerDrainService customerDrainService;
	
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		customerDrainService = ctx.getBean(CustomerDrainService.class);
	}
	

	@Test
	public void testSpringData() {
		Map<String, Object> params =new HashMap<>();
		long l = customerDrainService.gselectTotalNumber(params);
		System.out.println(l);
	}

}
