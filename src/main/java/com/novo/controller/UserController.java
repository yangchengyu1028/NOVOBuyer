package com.novo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.novo.entity.SupplierEntity;
import com.novo.entity.UserEntity;
import com.novo.entity.UserSupplierEntity;
import com.novo.service.ISupplierService;
import com.novo.service.IUserService;
import com.novo.service.IUserSupplierService;

@Controller("controller")
public class UserController {

	@Autowired
	IUserService userservice;
	@Autowired
	private ISupplierService supplierService;
	@Autowired
	private IUserSupplierService userSupplierService;

	@RequestMapping("login.novo")
	public ModelAndView toLogin() {
		ModelAndView mv = new ModelAndView("login");
		return mv;

	}

	// 用户登录 
	@RequestMapping("userLogin.novo")
	@ResponseBody
	public String login(String username, String password,HttpServletRequest request) {
		username = username.trim();
		password = password.trim();
		HttpSession session = request.getSession();
			UserEntity user = userservice.findUser(username, password);
			if(user!=null) {
				if(user.getState().equals("1")) {
					session.setAttribute("user", user);
					
					return "seccess";
				}else {
					return "该用户已失效！";
				}	
			}else {
				return "密码错误！";
			}
		
	}
	
	//退出登录
	@RequestMapping("outlogin.novo")
	public String outlogin(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.removeAttribute("user");
		return "redirect:/login.novo";
		
	}
	

}
