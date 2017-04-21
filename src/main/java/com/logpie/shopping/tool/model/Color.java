package com.logpie.shopping.tool.model;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.ForeignEntity;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.annotation.Entity;
import com.logpie.framework.db.basic.Model;
import com.logpie.shopping.tool.repository.ColorRepository;

@Entity(name = ColorRepository.DB_TABLE_COLOR)
public class Color extends Model {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = ColorRepository.DB_KEY_COLOR_ID, type = DataType.LONG)
	private Long id;

	@Column(name = ColorRepository.DB_KEY_COLOR_NAME, type = DataType.STRING)
	private String name;

	@ForeignEntity(name = ColorRepository.DB_KEY_COLOR_SHOP_ID, referencedTable = Shop.class)
	private Shop shop;

	public Color() {

	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param shop
	 */
	public Color(final Long id, final String name, final Shop shop) {
		this.id = id;
		this.name = name;
		this.shop = shop;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
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

	public void setShop(Shop shop) {
		this.shop = shop;
	}

}
