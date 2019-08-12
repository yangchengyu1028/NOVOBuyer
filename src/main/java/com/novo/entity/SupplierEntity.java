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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
/**
 * 供应商
 * @author ycy
 *
 */
@Entity
@Table(name="supplier")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SupplierEntity implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4745230367001928225L;
	private int id;
	private String name;//供货商名称
	private Set<UserBuySchemeEntity> ubs = new HashSet<UserBuySchemeEntity>();
	
	
	
	public SupplierEntity() {
	}
	
	
	public SupplierEntity(int id, String name, String money, String count, String rate,Set<UserBuySchemeEntity> ubs,UserEntity user) {
		this.id = id;
		this.name = name;
		this.ubs = ubs;
	}


	@Id
	@Column(name="s_id",unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="s_name",length=244)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(mappedBy = "supplier")
	@JsonIgnore
	public Set<UserBuySchemeEntity> getUbs() {
		return ubs;
	}


	public void setUbs(Set<UserBuySchemeEntity> ubs) {
		this.ubs = ubs;
	}

	
}
