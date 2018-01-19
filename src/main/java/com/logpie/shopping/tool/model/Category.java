package com.logpie.shopping.tool.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.logpie.dba.api.annotation.Entity;
import com.logpie.dba.api.annotation.ID;
import com.logpie.dba.api.annotation.ForeignKeyColumn;
import com.logpie.dba.api.annotation.Column;
import com.logpie.dba.api.annotation.Column.DataType;
import com.logpie.dba.api.annotation.AutoGenerate;
import com.logpie.dba.api.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.dba.api.basic.Model;
import com.logpie.shopping.tool.repository.CategoryRepository;

@Entity(name = CategoryRepository.DB_TABLE_CATEGORY)
public class Category extends Model {
	@ID
	@Column(name = CategoryRepository.DB_KEY_CATEGORY_ID, type = DataType.BIGINT)
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	private BigInteger id;

	@Column(name = CategoryRepository.DB_KEY_CATEGORY_NAME, type = DataType.STRING)
	private String name;

	@ForeignKeyColumn(name = CategoryRepository.DB_KEY_CATEGORY_SHOP_ID, referencedEntityClass = Shop.class)
	private Shop shop;

	private List<SubCategory> subcategories;

	public Category() {

	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param shop
	 */
	public Category(final BigInteger id, final String name, final Shop shop,
			final List<SubCategory> subcategories) {
		this.id = id;
		this.name = name;
		this.shop = shop;
		setSubcategories(subcategories);
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

	public List<SubCategory> getSubcategories() {
		return subcategories;
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

	public void setSubcategories(List<SubCategory> subcategories) {
		this.subcategories = subcategories == null ? new ArrayList<SubCategory>()
				: subcategories;
	}
}
