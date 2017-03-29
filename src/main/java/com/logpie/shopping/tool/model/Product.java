package com.logpie.shopping.tool.model;

import java.sql.Timestamp;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.ForeignEntity;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.annotation.Table;
import com.logpie.framework.db.basic.LogpieModel;
import com.logpie.shopping.tool.repository.ProductRepository;

@Table(name = ProductRepository.DB_TABLE_PRODUCT)
public class Product extends LogpieModel {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = ProductRepository.DB_KEY_PRODUCT_ID, type = DataType.LONG)
	private Long productId;
	@Column(name = ProductRepository.DB_KEY_PRODUCT_NAME, type = DataType.STRING)
	private String productName;
	@Column(name = ProductRepository.DB_KEY_PRODUCT_WEIGHT, type = DataType.INTEGER)
	private Integer productWeight;
	@AutoGenerate(strategy = AutoGenerateType.CurrentTime)
	@Column(name = ProductRepository.DB_KEY_PRODUCT_POST_DATE, type = DataType.TIMESTAMP)
	private Timestamp productPostDate;
	@ForeignEntity(name = ProductRepository.DB_KEY_PRODUCT_BRAND_ID, referencedTable = Brand.class)
	private Brand productBrand;
	@ForeignEntity(name = ProductRepository.DB_KEY_PRODUCT_SUBCATEGORY_ID, referencedTable = SubCategory.class)
	private SubCategory productSubCategory;
	@ForeignEntity(name = ProductRepository.DB_KEY_PRODUCT_COLOR_ID, referencedTable = Color.class)
	private Color productColor;
	@ForeignEntity(name = ProductRepository.DB_KEY_PRODUCT_SIZE_ID, referencedTable = Size.class)
	private Size productSize;
	@Column(name = ProductRepository.DB_KEY_PRODUCT_ORIGINAL_ID, type = DataType.STRING)
	private String productOriginalId;

	/**
	 * constructor for creating a product
	 * 
	 * @param productName
	 * @param productBrand
	 * @param productSubCategory
	 */
	public Product(String productName, Brand productBrand,
			SubCategory productSubCategory) {
		this(null, productName, new Integer(0), null, productBrand,
				productSubCategory, null, null, null);
	}

	/**
	 * 
	 * @param productId
	 * @param productName
	 * @param productWeight
	 * @param productPostDate
	 * @param productBrand
	 * @param productSubCategory
	 * @param productColor
	 * @param productSize
	 * @param productOriginalId
	 */
	public Product(Long productId, String productName, Integer productWeight,
			Timestamp productPostDate, Brand productBrand,
			SubCategory productSubCategory, Color productColor,
			Size productSize, String productOriginalId) {
		this.productId = productId;
		this.productName = productName;
		this.productWeight = productWeight;
		this.productPostDate = productPostDate;
		this.productBrand = productBrand;
		this.productSubCategory = productSubCategory;
		this.productColor = productColor;
		this.productSize = productSize;
		this.productOriginalId = productOriginalId;
	}

	public Long getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public Integer getProductWeight() {
		return productWeight;
	}

	public Timestamp getProductPostDate() {
		return productPostDate;
	}

	public Brand getProductBrand() {
		return productBrand;
	}

	public SubCategory getProductSubCategory() {
		return productSubCategory;
	}

	public Color getProductColor() {
		return productColor;
	}

	public Size getProductSize() {
		return productSize;
	}

	public String getProductOriginalId() {
		return productOriginalId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductWeight(Integer productWeight) {
		this.productWeight = productWeight;
	}

	public void setProductBrand(Brand productBrand) {
		this.productBrand = productBrand;
	}

	public void setProductSubCategory(SubCategory productSubCategory) {
		this.productSubCategory = productSubCategory;
	}

	public void setProductColor(Color productColor) {
		this.productColor = productColor;
	}

	public void setProductSize(Size productSize) {
		this.productSize = productSize;
	}

	public void setProductOriginalId(String productOriginalId) {
		this.productOriginalId = productOriginalId;
	}
}
