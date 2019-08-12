package com.novo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novo.dao.BasicDao;
import com.novo.dao.UserDao;
import com.novo.entity.UserEntity;
import com.novo.service.IUserService;
@Service("userService")
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserDao userDao;

	@Override
	public UserEntity findUser(String name,String password) {
	
		//String sql ="select * from user where user.u_name='"+ name +"'and user.u_password='"+password+"'";
		//return UserEntity.findObject(sql, obj);
		String hql ="from UserEntity u where u.name = ? and u.password=?";
		List<String> list=new ArrayList<String>() ;
			list.add(name);
			list.add(password);			
		
		return userDao.findObject(hql, list);
		
	}

	@Override
	public List<UserEntity> getAllList() {
		// TODO Auto-generated method stub
		return userDao.getListObject();
	}

}
