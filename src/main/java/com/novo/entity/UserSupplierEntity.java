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
 * 用户供应商中间表
 * @author ycy
 *
 */
@Entity
@Table(name="usersupplier")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSupplierEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 692202653978216267L;
	private int id;
	private String marryState;//匹配状态
	private UserEntity user;
	private SupplierEntity supplier;
	private int count;
	public UserSupplierEntity() {
	}
	public UserSupplierEntity(int id,String marryState, UserEntity user, SupplierEntity supplier,int count) {
		this.id = id;
		this.marryState = marryState;
		this.user = user;
		this.supplier = supplier;
		this.count = count;
	}
	@Id
	@Column(name = "us_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="us_marryState",length=8)
	public String getMarryState() {
		return marryState;
	}
	public void setMarryState(String marryState) {
		this.marryState = marryState;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="us_u_id")
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="us_s_id")
	public SupplierEntity getSupplier() {
		return supplier;
	}
	public void setSupplier(SupplierEntity supplier) {
		this.supplier = supplier;
	}
	@Column(name="us_count")
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
