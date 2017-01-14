package com.logpie.shopping.tool.model;

public class Brand implements LogpieModel {
	public static final String DB_KEY_BRAND_ID = "BrandId";
	public static final String DB_KEY_BRAND_NAME = "BrandName";

	private String mBrandId;
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
	public Brand(final String brandId, final String brandName) {
		this.mBrandId = brandId;
		this.mBrandName = brandName;
	}

	public String getmBrandId() {
		return mBrandId;
	}

	public void setmBrandId(String brandId) {
		this.mBrandId = brandId;
	}

	public String getmBrandName() {
		return mBrandName;
	}

	public void setmBrandName(String brandName) {
		this.mBrandName = brandName;
	}

	@Override
	public String getPrimaryKey() {
		return DB_KEY_BRAND_ID;
	}
}
