package com.logpie.shopping.tool.model;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.Table;
import com.logpie.shopping.tool.repository.BrandRepository;

@Table(name = BrandRepository.DB_TABLE_NAME_BRAND)
public class Brand extends LogpieModel {

	@Column(name = BrandRepository.DB_KEY_BRAND_ID, type = DataType.LONG, isPrimaryKey = true)
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	private long mBrandId;

	@Column(name = BrandRepository.DB_KEY_BRAND_NAME, type = DataType.STRING)
	private String mBrandName;

	/**
	 * Constructor for creating a brand
	 * 
	 * @param brandName
	 */
	public Brand(final String brandName) {
		this.mBrandName = brandName;
	}

	/**
	 * 
	 * @param brandId
	 * @param brandName
	 */
	public Brand(final long brandId, final String brandName) {
		this.mBrandId = brandId;
		this.mBrandName = brandName;
	}

	public long getBrandId() {
		return mBrandId;
	}

	public String getBrandName() {
		return mBrandName;
	}

	public void setBrandName(final String brandName) {
		this.mBrandName = brandName;
	}
}
