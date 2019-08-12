package com.novo.service;

import java.util.List;

import com.novo.entity.SupplierEntity;
import com.novo.entity.UserEntity;
import com.novo.entity.UserSupplierEntity;

public interface IUserSupplierService {
	
	public void save(UserSupplierEntity us);
	
	public Boolean exist(UserEntity user,SupplierEntity supp);
	/**
	 * 更新库存
	 * @param count
	 */
	public void updataOfNum(UserSupplierEntity us);
	/**
	 * 通过用户和商家ID获得实体
	 * @param userId
	 * @param suppId
	 * @return
	 */
	public UserSupplierEntity getUS(int userId,int suppId);
	/**
	 * 获取所有
	 * @return
	 */
	public List<UserSupplierEntity> getAll();
}
