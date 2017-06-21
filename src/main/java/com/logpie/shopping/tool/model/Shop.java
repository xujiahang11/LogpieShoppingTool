package com.logpie.shopping.tool.model;

import java.sql.Timestamp;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.Entity;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.basic.Model;
import com.logpie.shopping.tool.repository.ShopRepository;

@Entity(name = ShopRepository.DB_TABLE_SHOP)
public class Shop extends Model {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = ShopRepository.DB_KEY_SHOP_ID, type = DataType.LONG)
	private Long id;

	@Column(name = ShopRepository.DB_KEY_SHOP_NAME, type = DataType.STRING)
	private String name;

	@Column(name = ShopRepository.DB_KEY_SHOP_SMALL_LOGO, type = DataType.STRING)
	private String smallLogo;

	@Column(name = ShopRepository.DB_KEY_SHOP_LOGO, type = DataType.STRING)
	private String logo;

	@AutoGenerate(strategy = AutoGenerateType.CurrentTime)
	@Column(name = ShopRepository.DB_KEY_SHOP_REGISTER_DATE, type = DataType.TIMESTAMP)
	private Timestamp date;

	@Column(name = ShopRepository.DB_KEY_SHOP_PATH, type = DataType.STRING)
	private String path;

	@Column(name = ShopRepository.DB_KEY_SHOP_EXP, type = DataType.INTEGER)
	private Integer exp;

	public Shop() {

	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param smallLogo
	 * @param largeLogo
	 * @param date
	 * @param path
	 * @param exp
	 */
	public Shop(Long id, String name, String smallLogo, String logo,
			Timestamp date, String path, Integer exp) {
		this.id = id;
		this.name = name;
		this.smallLogo = smallLogo;
		this.logo = logo;
		this.date = date;
		this.path = path;
		this.exp = exp;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSmallLogo() {
		return smallLogo;
	}

	public String getLogo() {
		return logo;
	}

	public Timestamp getDate() {
		return date;
	}

	public String getPath() {
		return path;
	}

	public Integer getExp() {
		return exp;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSmallLogo(String smallLogo) {
		this.smallLogo = smallLogo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setExp(Integer exp) {
		this.exp = exp;
	}
}
