package com.logpie.shopping.tool.model;

import java.util.ArrayList;
import java.util.List;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.Entity;
import com.logpie.framework.db.annotation.ForeignKeyColumn;
import com.logpie.framework.db.annotation.ID;
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

	private List<SubCategory> subcategories;

	public Category() {

	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param shop
	 */
	public Category(final Long id, final String name, final Shop shop,
			final List<SubCategory> subcategories) {
		this.id = id;
		this.name = name;
		this.shop = shop;
		setSubcategories(subcategories);
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

	public List<SubCategory> getSubcategories() {
		return subcategories;
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

	public void setSubcategories(List<SubCategory> subcategories) {
		this.subcategories = subcategories == null ? new ArrayList<SubCategory>()
				: subcategories;
	}
}
