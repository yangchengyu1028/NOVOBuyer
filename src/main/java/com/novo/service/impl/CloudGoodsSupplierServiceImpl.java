package com.novo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novo.dao.CloudGoodsSupplierDao;
import com.novo.entity.CloudGoodsSupplierEntity;
import com.novo.entity.SupplierEntity;
import com.novo.entity.UserEntity;
import com.novo.service.ICloudGoodsSupplierService;
@Service
public class CloudGoodsSupplierServiceImpl implements ICloudGoodsSupplierService{
	@Autowired
	private CloudGoodsSupplierDao cloudGoodsSupplierDao;

	@Override
	public Object exist(CloudGoodsSupplierEntity cgs) {
		
		String hql = "from CloudGoodsSupplierEntity cgs where cgs.goodsSn='"+cgs.getGoodsSn()+"' and cgs.user.id="+cgs.getUser().getId();
		
		
		return cloudGoodsSupplierDao.findObject(hql);
	}

	@Override
	public void save(CloudGoodsSupplierEntity cgs) {
		cloudGoodsSupplierDao.sava(cgs);
		
	}

	@Override
	public void update(CloudGoodsSupplierEntity cgs) {
		cloudGoodsSupplierDao.updateObject(cgs);
	}

	@Override
	public int getAllNum(SupplierEntity supp,UserEntity user) {
		String hql = "select count(*) from CloudGoodsSupplierEntity cgs where cgs.supp.id="+supp.getId()+" and cgs.user.id="+user.getId();
		return cloudGoodsSupplierDao.getTotalNum2(hql);
	}

	@Override
	public List<CloudGoodsSupplierEntity> getListByTime(String time) {
		String hql = "from CloudGoodsSupplierEntity cgs where cgs.sale='1' and cgs.updateTime<"+time;
		return cloudGoodsSupplierDao.getListObject(hql);
	}


}
