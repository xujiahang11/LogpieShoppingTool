package com.logpie.shopping.tool.model;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.Entity;
import com.logpie.framework.db.annotation.ForeignKeyColumn;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.basic.Model;
import com.logpie.shopping.tool.repository.SubCategoryRepository;

@Entity(name = SubCategoryRepository.DB_TABLE_SUBCATEGORY)
public class SubCategory extends Model {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = SubCategoryRepository.DB_KEY_SUBCATEGORY_ID, type = DataType.LONG)
	private Long id;

	@Column(name = SubCategoryRepository.DB_KEY_SUBCATEGORY_NAME, type = DataType.STRING)
	private String name;

	@ForeignKeyColumn(name = SubCategoryRepository.DB_KEY_SUBCATEGORY_CATEGORY_ID, referencedEntityClass = Category.class)
	private Category category;

	public SubCategory() {

	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param category
	 * @param shop
	 */
	public SubCategory(final Long id, final String name, final Category category) {
		this.id = id;
		this.name = name;
		this.category = category;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
