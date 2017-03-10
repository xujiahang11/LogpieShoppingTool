package com.logpie.shopping.tool.model;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.Table;
import com.logpie.shopping.tool.repository.CategoryRepository;

@Table(name = CategoryRepository.DB_TABLE_NAME_CATEGORY)
public class Category extends LogpieModel {

	@Column(name = CategoryRepository.DB_KEY_CATEGORY_ID, type = DataType.LONG, isPrimaryKey = true)
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	private long mCategoryId;

	@Column(name = CategoryRepository.DB_KEY_CATEGORY_NAME, type = DataType.STRING)
	private String mCategoryName;

	/**
	 * Constructor for creating a category
	 * 
	 * @param categoryName
	 */
	public Category(final String categoryName) {
		this.mCategoryName = categoryName;
	}

	/**
	 * 
	 * @param categoryId
	 * @param categoryName
	 */
	public Category(final long categoryId, final String categoryName) {
		this.mCategoryId = categoryId;
		this.mCategoryName = categoryName;
	}

	public long getCategoryId() {
		return mCategoryId;
	}

	public String getCategoryName() {
		return mCategoryName;
	}

	public void setCategoryName(String categoryName) {
		this.mCategoryName = categoryName;
	}
}
