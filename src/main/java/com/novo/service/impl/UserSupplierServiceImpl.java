package com.novo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novo.dao.UserSupplierDao;
import com.novo.entity.SupplierEntity;
import com.novo.entity.UserEntity;
import com.novo.entity.UserSupplierEntity;
import com.novo.service.IUserSupplierService;
@Service("userSupplierService")
public class UserSupplierServiceImpl implements IUserSupplierService {
	@Autowired
	private UserSupplierDao userSupplierDao;
	
	@Override
	public void save(UserSupplierEntity us) {
		userSupplierDao.sava(us);

	}

	@Override
	public Boolean exist(UserEntity user, SupplierEntity supp) {
		String hql = "from UserSupplierEntity us where us.user.id="+user.getId()+" and us.supplier.id="+supp.getId();
		UserSupplierEntity us = userSupplierDao.findObject(hql);
		if(us==null) {
			return false;
		}
		
		return true;
	}

	@Override
	public void updataOfNum(UserSupplierEntity us) {
		userSupplierDao.updateObject(us);
		
	}

	@Override
	public UserSupplierEntity getUS(int userId, int suppId) {
		String hql = "from UserSupplierEntity where user.id="+userId+" and supplier.id="+suppId;
		return userSupplierDao.findObject(hql);
	}

	@Override
	public List<UserSupplierEntity> getAll() {
		// TODO Auto-generated method stub
		return userSupplierDao.getListObject();
	}

}
