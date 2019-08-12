package com.novo.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 用户
 * @author ycy
 *
 */
@Entity
@Table(name="user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEntity implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1643180155867041172L;
	private int id;
	private int relaId;
	private String name;
	private String password;
	private String state;
	private String phone;
	private String realName;
	
	public UserEntity() {
	
	}
	
	
	public UserEntity(int id, int relaId, String name, String password, String state, String phone,
			String realName) {
		this.id = id;
		this.relaId = relaId;
		this.name = name;
		this.password = password;
		this.state = state;
		this.phone = phone;
		this.realName = realName;
	}


	@Id
	@Column(name="u_id",unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="u_relaId")
	public int getRelaId() {
		return relaId;
	}
	public void setRelaId(int relaId) {
		this.relaId = relaId;
	}
	@Column(name="u_name",length=24)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="u_password",length=24)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="u_state",length=8)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name="u_phone",length=11)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name="u_realName",length=24)
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	
}
