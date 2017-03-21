package com.logpie.shopping.tool.model;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.ForeignEntity;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.annotation.Table;
import com.logpie.framework.db.util.LogpieModel;
import com.logpie.shopping.tool.repository.SubCategoryRepository;

@Table(name = SubCategoryRepository.DB_TABLE_SUBCATEGORY)
public class SubCategory extends LogpieModel {
	@ID
	@Column(name = SubCategoryRepository.DB_KEY_SUBCATEGORY_ID, type = DataType.LONG)
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	private Long subCategoryId;

	@Column(name = SubCategoryRepository.DB_KEY_SUBCATEGORY_NAME, type = DataType.STRING)
	private String subCategoryName;

	@ForeignEntity(name = SubCategoryRepository.DB_KEY_CATEGORY_ID, referencedTable = Category.class)
	private Category category;

	/**
	 * Constructor for creating a sub-category
	 * 
	 * @param subCategoryName
	 */
	public SubCategory(final String subCategoryName, final Category category) {
		this(null, subCategoryName, category);
	}

	/**
	 * 
	 * @param subCategoryId
	 * @param subCategoryName
	 */
	public SubCategory(final Long subCategoryId, final String subCategoryName,
			final Category category) {
		this.subCategoryId = subCategoryId;
		this.subCategoryName = subCategoryName;
		this.category = category;
	}

	public Long getSubCategoryId() {
		return subCategoryId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public Category getCategory() {
		return category;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
