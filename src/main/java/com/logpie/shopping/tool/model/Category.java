package com.logpie.shopping.tool.model;

import com.logpie.framework.db.annotation.DatabaseEntity;
import com.logpie.shopping.tool.repository.CategoryRepository;

@DatabaseEntity(table = CategoryRepository.DB_TABLE_NAME_CATEGORY)
public class Category extends LogpieModel {
	private String mCategoryId;
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
	public Category(final String categoryId, final String categoryName) {
		this.mCategoryId = categoryId;
		this.mCategoryName = categoryName;
	}

	public String getCategoryId() {
		return mCategoryId;
	}

	public void setCategoryId(String categoryId) {
		this.mCategoryId = categoryId;
	}

	public String getCategoryName() {
		return mCategoryName;
	}

	public void setCategoryName(String categoryName) {
		this.mCategoryName = categoryName;
	}
}
