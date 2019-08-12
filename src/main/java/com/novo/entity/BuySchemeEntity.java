package com.novo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 采购方案商品
 * @author ycy
 *
 */
@Entity
@Table(name="buyscheme")
@JsonIgnoreProperties(ignoreUnknown=true)
public class BuySchemeEntity implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1026792674848970314L;
	private int id;
	private String comName;
	private String spec;
	private String produceFact;
	private String licenseNo;
	private String barCode;
	private String drug;
	private String unit;
	private String erpNo;
	private float price; //实际采购价格
	private int reBuyNum;  //实际采购数量
	private int planBuyNum; //计划采购数量
	private String goodsSn; //商品货号
	private SupplierEntity supp; 
	private ChildSchemeEntity childScheme;
	public BuySchemeEntity() {
		
	}
	public BuySchemeEntity(int id, String comName, String spec, String produceFact, String licenseNo, String barCode,
			String drug, String unit, String erpNo, float price,int reBuyNum,int planBuyNum,String goodsSn, SupplierEntity supp, ChildSchemeEntity childScheme) {
		this.id = id;
		this.comName = comName;
		this.spec = spec;
		this.produceFact = produceFact;
		this.licenseNo = licenseNo;
		this.barCode = barCode;
		this.drug = drug;
		this.unit = unit;
		this.erpNo = erpNo;
		this.price = price;
		this.reBuyNum = reBuyNum;
		this.planBuyNum = planBuyNum;
		this.goodsSn = goodsSn;
		this.supp = supp;
		this.childScheme = childScheme;
	}
	@Id
	@Column(name = "bs_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="bs_comName",length=244)
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	@Column(name="bs_spec",length=244)
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	@Column(name="bs_produceFact",length=244)
	public String getProduceFact() {
		return produceFact;
	}
	public void setProduceFact(String produceFact) {
		this.produceFact = produceFact;
	}
	@Column(name="bs_licenseNo",length=244)
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	@Column(name="bs_barCode",length=80)
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	@Column(name="bs_drug",length=244)
	public String getDrug() {
		return drug;
	}
	public void setDrug(String drug) {
		this.drug = drug;
	}
	@Column(name="bs_unit",length=80)
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(name="bs_erpNo",length=80)
	public String getErpNo() {
		return erpNo;
	}
	public void setErpNo(String erpNo) {
		this.erpNo = erpNo;
	}
	@Column(name="bs_price")
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	@Column(name="bs_reBuyNum")
	public int getReBuyNum() {
		return reBuyNum;
	}
	public void setReBuyNum(int reBuyNum) {
		this.reBuyNum = reBuyNum;
	}
	@Column(name="bs_planBuyNum")
	public int getPlanBuyNum() {
		return planBuyNum;
	}
	public void setPlanBuyNum(int planBuyNum) {
		this.planBuyNum = planBuyNum;
	}
	@Column(name="bs_goodsSn")
	public String getGoodsSn() {
		return goodsSn;
	}
	public void setGoodsSn(String goodsSn) {
		this.goodsSn = goodsSn;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="bs_s_id")
	public SupplierEntity getSupp() {
		return supp;
	}
	public void setSupp(SupplierEntity supp) {
		this.supp = supp;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="bs_cs_id")
	public ChildSchemeEntity getChildScheme() {
		return childScheme;
	}
	public void setChildScheme(ChildSchemeEntity childScheme) {
		this.childScheme = childScheme;
	}
	
	
	
}
