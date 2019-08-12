package com.novo.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.novo.entity.UserEntity;


public class MyInterceptor implements HandlerInterceptor{
		//预处理，调用方法之前，如果返回false就不用再执行方法（controller里面的方法）
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			//获得session的用户对象，如果为null代表没有登录
			HttpSession session = request.getSession();
			UserEntity obj = (UserEntity) session.getAttribute("user");
			if(obj==null){
				response.sendRedirect("login.novo");
				return false;
			 }
			if(obj.getState().equals("0")) {
				response.sendRedirect("login.novo");
				return false;
			}
			return true;
		}
	   //渲染之前，我们可以决定跳转的地方
		public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
				ModelAndView modelAndView) throws Exception {
		 // modelAndView.addObject("error", );
		/*  modelAndView.setViewName("login");*/
		}

	   // 调用方法之后
		public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
				throws Exception {
			
		}


}
