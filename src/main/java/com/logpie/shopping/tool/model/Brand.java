package com.logpie.shopping.tool.model;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.annotation.Table;
import com.logpie.framework.db.basic.LogpieModel;
import com.logpie.shopping.tool.repository.BrandRepository;

@Table(name = BrandRepository.DB_TABLE_BRAND)
public class Brand extends LogpieModel {
	@ID
	@Column(name = BrandRepository.DB_KEY_BRAND_ID, type = DataType.LONG)
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	private Long brandId;

	@Column(name = BrandRepository.DB_KEY_BRAND_NAME, type = DataType.STRING)
	private String brandName;

	/**
	 * Constructor for creating a brand
	 * 
	 * @param brandName
	 */
	public Brand(final String brandName) {
		this(null, brandName);
	}

	/**
	 * 
	 * @param brandId
	 * @param brandName
	 */
	public Brand(final Long brandId, final String brandName) {
		this.brandId = brandId;
		this.brandName = brandName;
	}

	public Long getBrandId() {
		return brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(final String brandName) {
		this.brandName = brandName;
	}
}
