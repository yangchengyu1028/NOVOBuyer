package com.novo.service;

import java.util.List;

import com.novo.entity.CloudGoodsSupplierEntity;
import com.novo.entity.SupplierEntity;
import com.novo.entity.UserEntity;

public interface ICloudGoodsSupplierService {
	/**
	 * 判断数据库是否存在该数据
	 * @param cgs
	 * @return
	 */
	public Object exist(CloudGoodsSupplierEntity cgs);
	/**
	 * 若不存在则插入
	 * @param cgs
	 */
	public void save(CloudGoodsSupplierEntity cgs);
	/**
	 * 添加或者修改
	 * @param cgs
	 */
	public void update(CloudGoodsSupplierEntity cgs);
	/**
	 * 得到该供应商的商品总数
	 * @param supp
	 * @return
	 */
	public int getAllNum(SupplierEntity supp,UserEntity user);
	/**
	 * 查询某个时间前未更新的商品
	 * @param time
	 * @return
	 */
	public List<CloudGoodsSupplierEntity> getListByTime(String time);
}
