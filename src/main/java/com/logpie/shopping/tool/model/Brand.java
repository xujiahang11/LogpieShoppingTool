package com.logpie.shopping.tool.model;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.ForeignEntity;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.annotation.Entity;
import com.logpie.framework.db.basic.Model;
import com.logpie.shopping.tool.repository.BrandRepository;

@Entity(name = BrandRepository.DB_TABLE_BRAND)
public class Brand extends Model {
	@ID
	@Column(name = BrandRepository.DB_KEY_BRAND_ID, type = DataType.LONG)
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	private Long id;

	@Column(name = BrandRepository.DB_KEY_BRAND_NAME, type = DataType.STRING)
	private String name;

	@ForeignEntity(name = BrandRepository.DB_KEY_BRAND_SHOP_ID, referencedTable = Shop.class)
	private Shop shop;

	public Brand() {

	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param shop
	 */
	public Brand(final Long id, final String name, final Shop shop) {
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
