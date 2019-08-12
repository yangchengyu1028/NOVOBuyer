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
 * 供应商商品表（云商品和供应商中间表）
 * @author ycy
 *
 */
@Entity
@Table(name = "cloudgoodssupplier")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CloudGoodsSupplierEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5227084541165569943L;

	private int id;
	private SupplierEntity supp;
	private CloudGoodsEntity goods;
	private int stock; //库存
	private String price; //价格
	private String expiryDate; //效期
	private String goodsSn; //商品货号
	private String sale;//是否商家 0未上架  1上架
	private UserEntity user;
	private String expiration_date; //有效期（时间段）
	private String productiontime;  //生产日期
	private String goods_packing;//中包装
	private String updateTime;//修改时间戳
	private String spec;//规格
	public CloudGoodsSupplierEntity() {
		
	}
	
	
	public CloudGoodsSupplierEntity(int id, SupplierEntity supp, CloudGoodsEntity goods, int stock, String price,String expiryDate,String goodsSn,String sale,UserEntity user,String expiration_date,String productiontime,String goods_packing,String updateTime,String spec) {
		super();
		this.id = id;
		this.supp = supp;
		this.goods = goods;
		this.stock = stock;
		this.price = price;
		this.goodsSn = goodsSn;
		this.sale = sale;
		this.user = user;
		this.expiration_date = expiration_date;
		this.productiontime = productiontime;
		this.goods_packing = goods_packing;
		this.updateTime = updateTime;
		this.spec = spec;
	}

	@Column(name="cs_spec",columnDefinition="varchar(100) default '暂无'")
	public String getSpec() {
		return spec;
	}


	public void setSpec(String spec) {
		this.spec = spec;
	}


	@Id
	@Column(name="cs_id",unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cs_s_id")
	public SupplierEntity getSupp() {
		return supp;
	}
	public void setSupp(SupplierEntity supp) {
		this.supp = supp;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cs_c_id")
	public CloudGoodsEntity getGoods() {
		return goods;
	}

	public void setGoods(CloudGoodsEntity goods) {
		this.goods = goods;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cs_u_id")
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	@Column(name="cs_stock")
	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}

	@Column(name="cs_price")
	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}

	@Column(name="cs_expiryDate")
	public String getExpiryDate() {
		return expiryDate;
	}


	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Column(name="cs_goodsSn")
	public String getGoodsSn() {
		return goodsSn;
	}


	public void setGoodsSn(String goodsSn) {
		this.goodsSn = goodsSn;
	}
	@Column(name="cs_sale")
	public String getSale() {
		return sale;
	}
	public void setSale(String sale) {
		this.sale = sale;
	}
	@Column(name="cs_expiration_date",columnDefinition="varchar(128) default '0'")
	public String getExpiration_date() {
		return expiration_date;
	}
	public void setExpiration_date(String expiration_date) {
		this.expiration_date = expiration_date;
	}
	@Column(name="cs_productiontime",columnDefinition="varchar(128) default '0'")
	public String getProductiontime() {
		return productiontime;
	}
	public void setProductiontime(String productiontime) {
		this.productiontime = productiontime;
	}
	@Column(name="cs_goods_packing",columnDefinition="varchar(10) default '1'")
	public String getGoods_packing() {
		return goods_packing;
	}
	public void setGoods_packing(String goods_packing) {
		this.goods_packing = goods_packing;
	}

	@Column(name="cs_updateTime",columnDefinition="varchar(100) default '0'")
	public String getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
	
	
}
