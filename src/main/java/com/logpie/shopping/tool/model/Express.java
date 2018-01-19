package com.logpie.shopping.tool.model;

import com.logpie.dba.api.annotation.Entity;
import com.logpie.dba.api.annotation.ID;
import com.logpie.dba.api.annotation.ForeignKeyColumn;
import com.logpie.dba.api.annotation.Column;
import com.logpie.dba.api.annotation.Column.DataType;
import com.logpie.dba.api.annotation.AutoGenerate;
import com.logpie.dba.api.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.dba.api.basic.Model;
import com.logpie.shopping.tool.repository.ExpressRepository;

import java.math.BigInteger;

@Entity(name = ExpressRepository.DB_TABLE_EXPRESS)
public class Express extends Model {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = ExpressRepository.DB_KEY_EXPRESS_ID, type = DataType.BIGINT)
	private BigInteger id;

	@Column(name = ExpressRepository.DB_KEY_EXPRESS_NAME, type = DataType.STRING)
	private String name;

	@Column(name = ExpressRepository.DB_KEY_EXPRESS_IS_INTERNATIONAL, type = DataType.BOOLEAN)
	private Boolean isInternational;

	@ForeignKeyColumn(name = ExpressRepository.DB_KEY_EXPRESS_SHOP_ID, referencedEntityClass = Shop.class)
	private Shop shop;

	public Express() {

	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param isInternational
	 * @param shop
	 */
	public Express(final BigInteger id, final String name,
				   final Boolean isInternational, final Shop shop) {
		this.id = id;
		this.name = name;
		this.isInternational = isInternational;
		this.shop = shop;
	}

	public BigInteger getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Boolean getIsInternational() {
		return isInternational;
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

	public void setIsInternational(Boolean isInternational) {
		this.isInternational = isInternational;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
}
