package com.novo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 云商品库商品
 * @author ycy
 *
 */
@Entity
@Table(name="cloudgoods")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CloudGoodsEntity implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9008993120070372L;

	private int id;
	private String comName; // 通用名
	private String spec; // 规格
	private String produceFact; // 生产厂家
	private String licenseNo; // 批准文号
	private String barCode; // 条形码
	private Integer uptime; // 
	private String specDroduct;//规格乘积
	public CloudGoodsEntity() {
		
	}

	public CloudGoodsEntity(int id, String comName, String spec, String produceFact, String licenseNo, String barCode,
			Integer uptime ,String specDroduct) {
		this.id = id;
		this.comName = comName;
		this.spec = spec;
		this.produceFact = produceFact;
		this.licenseNo = licenseNo;
		this.barCode = barCode;
		this.uptime = uptime;
		this.specDroduct = specDroduct;
	}
	@Id
	@Column(name="c_id",unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name="c_comName",length=80)
	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}
	@Column(name="c_spec",length=80)
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	@Column(name="c_productFact",length=244)
	public String getProduceFact() {
		return produceFact;
	}

	public void setProduceFact(String produceFact) {
		this.produceFact = produceFact;
	}
	@Column(name="c_licenseNo",length=80)
	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	@Column(name="c_barCode",length=80)
	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	@Column(name="c_uptime",length=10)
	public Integer getUptime() {
		return uptime;
	}

	public void setUptime(Integer uptime) {
		this.uptime = uptime;
	}
	@Column(name="c_specDroduct",length=40)
	public String getSpecDroduct() {
		return specDroduct;
	}

	public void setSpecDroduct(String specDroduct) {
		this.specDroduct = specDroduct;
	}

	
	
	
}
