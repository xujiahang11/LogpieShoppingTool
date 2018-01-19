package com.logpie.shopping.tool.model;

import com.logpie.dba.api.annotation.Entity;
import com.logpie.dba.api.annotation.ID;
import com.logpie.dba.api.annotation.ForeignKeyColumn;
import com.logpie.dba.api.annotation.Column;
import com.logpie.dba.api.annotation.Column.DataType;
import com.logpie.dba.api.annotation.AutoGenerate;
import com.logpie.dba.api.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.dba.api.basic.Model;
import com.logpie.shopping.tool.repository.BrandRepository;

import java.math.BigInteger;

@Entity(name = BrandRepository.DB_TABLE_BRAND)
public class Brand extends Model {
	@ID
	@Column(name = BrandRepository.DB_KEY_BRAND_ID, type = DataType.BIGINT)
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	private BigInteger id;

	@Column(name = BrandRepository.DB_KEY_BRAND_NAME, type = DataType.STRING)
	private String name;

	@ForeignKeyColumn(name = BrandRepository.DB_KEY_BRAND_SHOP_ID, referencedEntityClass = Shop.class)
	private Shop shop;

	public Brand() {

	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param shop
	 */
	public Brand(final BigInteger id, final String name, final Shop shop) {
		this.id = id;
		this.name = name;
		this.shop = shop;
	}

	public BigInteger getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Shop getShop() {
		return shop;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
}
