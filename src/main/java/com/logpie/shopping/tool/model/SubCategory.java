package com.logpie.shopping.tool.model;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.ForeignEntity;
import com.logpie.framework.db.annotation.Table;
import com.logpie.shopping.tool.repository.CategoryRepository;
import com.logpie.shopping.tool.repository.SubCategoryRepository;

@Table(name = SubCategoryRepository.DB_TABLE_NAME_SUBCATEGORY)
public class SubCategory extends LogpieModel {

	@Column(name = SubCategoryRepository.DB_KEY_SUBCATEGORY_ID, type = DataType.LONG, isPrimaryKey = true)
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	private long mSubCategoryId;

	@Column(name = SubCategoryRepository.DB_KEY_SUBCATEGORY_NAME, type = DataType.STRING)
	private String mSubCategoryName;

	@ForeignEntity(name = SubCategoryRepository.DB_KEY_CATEGORY_ID, referencedTable = Category.class, referencedColumn = CategoryRepository.DB_KEY_CATEGORY_ID)
	private Category mCategory;

	/**
	 * Constructor for creating a sub-category
	 * 
	 * @param subCategoryName
	 */
	public SubCategory(final String subCategoryName, final Category category) {
		this.mSubCategoryName = subCategoryName;
		this.mCategory = category;
	}

	/**
	 * 
	 * @param subCategoryId
	 * @param subCategoryName
	 */
	public SubCategory(final long subCategoryId, final String subCategoryName,
			final Category category) {
		this.mSubCategoryId = subCategoryId;
		this.mSubCategoryName = subCategoryName;
		this.mCategory = category;
	}

	public long getSubCategoryId() {
		return mSubCategoryId;
	}

	public String getSubCategoryName() {
		return mSubCategoryName;
	}

	public Category getCategory() {
		return mCategory;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.mSubCategoryName = subCategoryName;
	}

	public void setCategory(Category category) {
		this.mCategory = category;
	}

}
