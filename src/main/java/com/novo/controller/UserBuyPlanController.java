package com.novo.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novo.entity.BuyPlanEntity;
import com.novo.entity.UserBuyPlanEntity;
import com.novo.entity.UserEntity;
import com.novo.service.IBuyPlanService;
import com.novo.service.IUserBuyPlanService;
import com.novo.util.DateToString;
import com.novo.util.PageBean;

@Controller
public class UserBuyPlanController {
	
	@Autowired
	private IUserBuyPlanService userBuyPlanService;
	
	@Autowired
	private IBuyPlanService buyPlanService;
	
	@RequestMapping("buyerPlan.novo")
	@ResponseBody
	public ModelAndView localGoods() {
		ModelAndView mv = new ModelAndView("buyerPlan");
		return mv;
	}
	
	@RequestMapping("getUserBuyPlan.novo")
	@ResponseBody
	public PageBean getUserBuyPlan(String planTime, String manageTime, String state, int pageNo, int pageSize,HttpSession session) {
		
		UserEntity user = (UserEntity) session.getAttribute("user");
		return userBuyPlanService.getList(planTime, manageTime, state, pageNo, pageSize,user);
	
	}
	
	@RequestMapping("planDetails.novo")
	@ResponseBody
	public PageBean planDetails(String comName,String state,int id,int pageNo,int pageSize) {
		
		return buyPlanService.getList(comName, state, id, pageNo, pageSize);
	}
	
	@RequestMapping("delUserBuyPlan.novo")
	@ResponseBody
	public String delUserBuyPlan(int id) {
		
		UserBuyPlanEntity userBuyPlanEntity = userBuyPlanService.getById(id);
		userBuyPlanEntity.setState("9");
		userBuyPlanService.update(userBuyPlanEntity);
		
		return "success";
	}
	
	@RequestMapping("subUserBuyPlan.novo")
	@ResponseBody
	public String subUserBuyPlan(int id) {
		Date a = new Date();
		DateToString d = new DateToString();
		String manageTime = d.getStringTime(a);
		UserBuyPlanEntity ubp = userBuyPlanService.getById(id);
		ubp.setManageTime(manageTime);
		ubp.setState("1");
		userBuyPlanService.update(ubp);
		return "success";
	}
}
