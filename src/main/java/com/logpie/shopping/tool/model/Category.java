package com.logpie.shopping.tool.model;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.Table;
import com.logpie.shopping.tool.repository.CategoryRepository;

@Table(name = CategoryRepository.DB_TABLE_CATEGORY)
public class Category extends LogpieModel {

	@Column(name = CategoryRepository.DB_KEY_CATEGORY_ID, type = DataType.LONG, isPrimaryKey = true)
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	private Long categoryId;

	@Column(name = CategoryRepository.DB_KEY_CATEGORY_NAME, type = DataType.STRING)
	private String categoryName;

	/**
	 * Constructor for creating a category
	 * 
	 * @param categoryName
	 */
	public Category(final String categoryName) {
		this(null, categoryName);
	}

	/**
	 * 
	 * @param categoryId
	 * @param categoryName
	 */
	public Category(final Long categoryId, final String categoryName) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
