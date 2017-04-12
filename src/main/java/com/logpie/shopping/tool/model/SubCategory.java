package com.logpie.shopping.tool.model;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.ForeignEntity;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.annotation.Table;
import com.logpie.framework.db.basic.Model;
import com.logpie.shopping.tool.repository.SubCategoryRepository;

@Table(name = SubCategoryRepository.DB_TABLE_SUBCATEGORY)
public class SubCategory extends Model {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = SubCategoryRepository.DB_KEY_SUBCATEGORY_ID, type = DataType.LONG)
	private Long id;

	@Column(name = SubCategoryRepository.DB_KEY_SUBCATEGORY_NAME, type = DataType.STRING)
	private String name;

	@ForeignEntity(name = SubCategoryRepository.DB_KEY_SUBCATEGORY_CATEGORY_ID, referencedTable = Category.class)
	private Category category;

	@ForeignEntity(name = SubCategoryRepository.DB_KEY_SUBCATEGORY_SHOP_ID, referencedTable = Shop.class)
	private Shop shop;

	public SubCategory() {

	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param category
	 * @param shop
	 */
	public SubCategory(final Long id, final String name,
			final Category category, final Shop shop) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.shop = shop;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Category getCategory() {
		return category;
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

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

}
