package com.logpie.shopping.tool.model;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.ForeignKeyColumn;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.annotation.Entity;
import com.logpie.framework.db.basic.Model;
import com.logpie.shopping.tool.repository.CategoryRepository;

@Entity(name = CategoryRepository.DB_TABLE_CATEGORY)
public class Category extends Model {
	@ID
	@Column(name = CategoryRepository.DB_KEY_CATEGORY_ID, type = DataType.LONG)
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	private Long id;

	@Column(name = CategoryRepository.DB_KEY_CATEGORY_NAME, type = DataType.STRING)
	private String name;

	@ForeignKeyColumn(name = CategoryRepository.DB_KEY_CATEGORY_SHOP_ID, referencedEntityClass = Shop.class)
	private Shop shop;

	public Category() {

	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param shop
	 */
	public Category(final Long id, final String name, final Shop shop) {
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
