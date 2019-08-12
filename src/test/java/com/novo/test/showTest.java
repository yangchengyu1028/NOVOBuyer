package com.novo.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.novo.dao.UserDao;
import com.novo.dto.UserBuySchemeDto;
import com.novo.entity.SupplierEntity;
import com.novo.entity.UserBuyPlanEntity;
import com.novo.entity.UserBuySchemeEntity;
import com.novo.entity.UserEntity;
import com.novo.entity.UserProductEntity;
import com.novo.entity.UserSupplierEntity;
import com.novo.service.ISupplierService;
import com.novo.service.IUserBuyPlanService;
import com.novo.service.IUserBuySchemeService;
import com.novo.service.IUserProductService;
import com.novo.service.IUserSupplierService;
import com.novo.util.PageBean;
import com.novo.vo.GoodsVo;

public class showTest {
	private IUserProductService userProductService;
	private IUserBuyPlanService userBuyPlanService;
	private IUserBuySchemeService userBuySchemeService;
	private ISupplierService supplierService;
	private IUserSupplierService userSupplierService;
	
	@Before
	public void before(){
		ClassPathXmlApplicationContext app=new ClassPathXmlApplicationContext("hibernate-spring.xml");
		userProductService= (IUserProductService) app.getBean("userProductService");
		userBuyPlanService=(IUserBuyPlanService)app.getBean("userBuyPlanService");
		userBuySchemeService=(IUserBuySchemeService)app.getBean("userBuySchemeService");
		supplierService=(ISupplierService)app.getBean("supplierService");
		userSupplierService=(IUserSupplierService)app.getBean("userSupplierService");
		
	}
	
	@Test
	public void getBuyPlan() {
		UserEntity user = new UserEntity();
		user.setId(1);
		PageBean pb =  userBuyPlanService.getList("", "", "0", 1, 2, user);
		List<UserBuyPlanEntity> list = (List<UserBuyPlanEntity>) pb.getList();
		for (UserBuyPlanEntity userBuyPlanEntity : list) {
			System.out.println(userBuyPlanEntity.getTotalPrice());
		}
	}
	
	@Test
	public void getBuyScheme() {
		UserEntity user = new UserEntity();
		user.setId(1);
		
		PageBean pb =  userBuySchemeService.getList("", "", "0", 1, 5, user);
		List<UserBuySchemeEntity> list = (List<UserBuySchemeEntity>) pb.getList();
		for (UserBuySchemeEntity u : list) {
			Set<SupplierEntity> set = u.getSupplier();
			for (SupplierEntity s : set) {
				System.out.println(s.getName());
			}
		}
		
	}
	
	@Test
	public void t1() {
		String[] goodId = {"1","2"};
		String[] supplierId = {"1","2"};
		String[] buyNum = {"1","2"};
		UserEntity user = new UserEntity();
		user.setId(1);
		userBuySchemeService.determineScheme(1, goodId, supplierId, buyNum, user);
	}
	@Test
	public void t2() {
		UserEntity user = new UserEntity();
		user.setName("杨承谕");
		SupplierEntity supp = new SupplierEntity();
		supp.setName("ycy");
		UserSupplierEntity us= new UserSupplierEntity();
		us.setSupplier(supp);
		us.setUser(user);
		userSupplierService.save(us);
	}
	@Test
	public void t3() {
		SupplierEntity supp = new SupplierEntity();
		supp.setId(25);
		supp.setName("yyc");
		supplierService.save(supp);
	}
}
