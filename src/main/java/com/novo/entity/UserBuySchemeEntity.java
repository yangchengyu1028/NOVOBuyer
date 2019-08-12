package com.novo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.sql.HSQLCaseFragment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
/**
 * 采购方案
 * @author ycy
 *
 */
@Entity
@Table(name="userbuyscheme")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserBuySchemeEntity implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6999018442310267859L;
	private int id;
	private String name;
	private UserEntity user;
	private String addTime; //添加时间
	private String subTime; //提交时间
	private int planBuyNum; //计划采购数量
	private int reBuyNum; //实际采购数量
	private float totalPrice; //采购总价格
	private String state; //采购方案状态
	private UserBuyPlanEntity ubp;
	private Set<SupplierEntity> supplier = new HashSet<SupplierEntity>();
	public UserBuySchemeEntity() {
		
	}
	public UserBuySchemeEntity(int id,String name, UserEntity user, String addTime, String subTime, int planBuyNum, int reBuyNum,
			float totalPrice, String state,UserBuyPlanEntity ubp,Set<SupplierEntity> supplier) {
		this.id = id;
		this.name = name;
		this.user = user;
		this.addTime = addTime;
		this.subTime = subTime;
		this.planBuyNum = planBuyNum;
		this.reBuyNum = reBuyNum;
		this.totalPrice = totalPrice;
		this.state = state;
		this.ubp = ubp;
		this.supplier = supplier;
	}
	@Id
	@Column(name = "ubs_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="ubs_name",length=244)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ubs_u_id")
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	@Column(name="ubs_addTime",length=24)
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	@Column(name="ubs_subTime",length=24)
	public String getSubTime() {
		return subTime;
	}
	public void setSubTime(String subTime) {
		this.subTime = subTime;
	}
	@Column(name="ubs_planBuyNum")
	public int getPlanBuyNum() {
		return planBuyNum;
	}
	public void setPlanBuyNum(int planBuyNum) {
		this.planBuyNum = planBuyNum;
	}
	@Column(name="ubs_reBuyNum")
	public int getReBuyNum() {
		return reBuyNum;
	}
	public void setReBuyNum(int reBuyNum) {
		this.reBuyNum = reBuyNum;
	}
	@Column(name="ubs_totalPrice")
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	@Column(name="ubs_state",length=8)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ubs_ubp_id",insertable=true,unique=true)
	public UserBuyPlanEntity getUbp() {
		return ubp;
	}
	public void setUbp(UserBuyPlanEntity ubp) {
		this.ubp = ubp;
	}
	@ManyToMany(targetEntity = SupplierEntity.class, fetch = FetchType.EAGER)    
    @JoinTable(name = "ubssupplier", joinColumns = @JoinColumn(name = "ubss_ubs_id"), inverseJoinColumns = @JoinColumn(name = "ubss_s_id"))    
	public Set<SupplierEntity> getSupplier() {
		return supplier;
	}
	public void setSupplier(Set<SupplierEntity> supplier) {
		this.supplier = supplier;
	}
	
	
	
}
