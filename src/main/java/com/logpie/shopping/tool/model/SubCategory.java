package com.logpie.shopping.tool.model;

import com.logpie.dba.api.annotation.Entity;
import com.logpie.dba.api.annotation.ID;
import com.logpie.dba.api.annotation.ForeignKeyColumn;
import com.logpie.dba.api.annotation.Column;
import com.logpie.dba.api.annotation.Column.DataType;
import com.logpie.dba.api.annotation.AutoGenerate;
import com.logpie.dba.api.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.dba.api.basic.Model;
import com.logpie.shopping.tool.repository.SubCategoryRepository;

import java.math.BigInteger;

@Entity(name = SubCategoryRepository.DB_TABLE_SUBCATEGORY)
public class SubCategory extends Model {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = SubCategoryRepository.DB_KEY_SUBCATEGORY_ID, type = DataType.BIGINT)
	private BigInteger id;

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
	 */
	public SubCategory(final BigInteger id, final String name, final Category category) {
		this.id = id;
		this.name = name;
		this.category = category;
	}

	public BigInteger getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Category getCategory() {
		return category;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
