package com.logpie.shopping.tool.model;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.ForeignKeyColumn;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.annotation.Entity;
import com.logpie.framework.db.basic.Model;
import com.logpie.shopping.tool.repository.AdminRepository;

@Entity(name = AdminRepository.DB_TABLE_ADMIN)
public class Admin extends Model {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = AdminRepository.DB_KEY_ADMIN_ID, type = DataType.LONG)
	private Long id;

	@Column(name = AdminRepository.DB_KEY_ADMIN_NAME, type = DataType.STRING)
	private String name;

	@Column(name = AdminRepository.DB_KEY_ADMIN_PHONE, type = DataType.STRING)
	private String phone;

	@Column(name = AdminRepository.DB_KEY_ADMIN_WECHAT, type = DataType.STRING)
	private String wechat;

	@Column(name = AdminRepository.DB_KEY_ADMIN_PROFIT_PERCENTAGE, type = DataType.FLOAT)
	private Float profitPercentage;

	@ForeignKeyColumn(name = AdminRepository.DB_KEY_ADMIN_SHOP_ID, referencedEntityClass = Shop.class)
	private Shop shop;

	public Admin() {

	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param phone
	 * @param wechat
	 * @param profitPercentage
	 * @param shop
	 */
	public Admin(Long id, String name, String phone, String wechat,
			Float profitPercentage, Shop shop) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.wechat = wechat;
		this.profitPercentage = profitPercentage;
		this.shop = shop;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getWechat() {
		return wechat;
	}

	public Float getProfitPercentage() {
		return profitPercentage;
	}

	public Shop getShop() {
		return shop;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public void setProfitPercentage(Float profitPercentage) {
		this.profitPercentage = profitPercentage;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
}
