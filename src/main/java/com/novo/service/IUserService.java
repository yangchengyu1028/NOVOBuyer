package com.novo.service;

import java.util.List;

import com.novo.entity.UserEntity;

public interface IUserService {
	
	//查找用户
	public UserEntity findUser(String name,String password) ;
	/**
	 * 得到所有用户	
	 * @return
	 */
	public List<UserEntity> getAllList();

}
