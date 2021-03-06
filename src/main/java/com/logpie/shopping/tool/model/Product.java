package com.logpie.shopping.tool.model;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import com.logpie.dba.api.annotation.Entity;
import com.logpie.dba.api.annotation.ID;
import com.logpie.dba.api.annotation.ForeignKeyColumn;
import com.logpie.dba.api.annotation.Column;
import com.logpie.dba.api.annotation.Column.DataType;
import com.logpie.dba.api.annotation.AutoGenerate;
import com.logpie.dba.api.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.dba.api.basic.Model;
import com.logpie.shopping.tool.repository.ProductRepository;

@Entity(name = ProductRepository.DB_TABLE_PRODUCT)
public class Product extends Model {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = ProductRepository.DB_KEY_PRODUCT_ID, type = DataType.BIGINT)
	private BigInteger id;

	@Column(name = ProductRepository.DB_KEY_PRODUCT_NAME, type = DataType.STRING)
	private String name;

	@AutoGenerate(strategy = AutoGenerateType.CurrentTime)
	@Column(name = ProductRepository.DB_KEY_PRODUCT_POST_DATE, type = DataType.TIMESTAMP)
	private Timestamp postDate;

	@ForeignKeyColumn(name = ProductRepository.DB_KEY_PRODUCT_BRAND_ID, referencedEntityClass = Brand.class)
	private Brand brand;

	@ForeignKeyColumn(name = ProductRepository.DB_KEY_PRODUCT_SUBCATEGORY_ID, referencedEntityClass = SubCategory.class)
	private SubCategory subCategory;

	@Column(name = ProductRepository.DB_KEY_PRODUCT_NOTE, type = DataType.STRING)
	private String note;

	@ForeignKeyColumn(name = ProductRepository.DB_KEY_PRODUCT_SHOP_ID, referencedEntityClass = Shop.class)
	private Shop shop;

	private List<ProductConfig> configs;

	public Product() {

	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param postDate
	 * @param brand
	 * @param subCategory
	 * @param shop
	 */
	public Product(final BigInteger id, final String name, final Timestamp postDate,
			final Brand brand, final SubCategory subCategory,
			final List<ProductConfig> configs, final String note,
			final Shop shop) {
		this.id = id;
		this.name = name;
		this.postDate = postDate;
		this.brand = brand;
		this.subCategory = subCategory;
		this.configs = configs;
		this.note = note;
		this.shop = shop;
	}

	public BigInteger getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Timestamp getPostDate() {
		return postDate;
	}

	public Brand getBrand() {
		return brand;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public List<ProductConfig> getConfigs() {
		return configs;
	}

	public String getNote() {
		return note;
	}

	public Shop getShop() {
		return shop;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPostDate(Timestamp postDate) {
		this.postDate = postDate;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public void setConfigs(List<ProductConfig> configs) {
		this.configs = configs;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
}
