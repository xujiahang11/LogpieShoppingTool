package com.logpie.shopping.tool.model;

import java.sql.Timestamp;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.Entity;
import com.logpie.framework.db.annotation.ForeignKeyColumn;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.basic.Model;
import com.logpie.shopping.tool.repository.ProductRepository;

@Entity(name = ProductRepository.DB_TABLE_PRODUCT)
public class Product extends Model {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = ProductRepository.DB_KEY_PRODUCT_ID, type = DataType.LONG)
	private Long id;

	@Column(name = ProductRepository.DB_KEY_PRODUCT_NAME, type = DataType.STRING)
	private String name;

	@Column(name = ProductRepository.DB_KEY_PRODUCT_WEIGHT, type = DataType.INTEGER)
	private Integer weight;

	@AutoGenerate(strategy = AutoGenerateType.CurrentTime)
	@Column(name = ProductRepository.DB_KEY_PRODUCT_POST_DATE, type = DataType.TIMESTAMP)
	private Timestamp postDate;

	@ForeignKeyColumn(name = ProductRepository.DB_KEY_PRODUCT_BRAND_ID, referencedEntityClass = Brand.class)
	private Brand brand;

	@ForeignKeyColumn(name = ProductRepository.DB_KEY_PRODUCT_SUBCATEGORY_ID, referencedEntityClass = SubCategory.class)
	private SubCategory subCategory;

	@Column(name = ProductRepository.DB_KEY_PRODUCT_ORIGINAL_ID, type = DataType.STRING)
	private String originalId;

	@Column(name = ProductRepository.DB_KEY_PRODUCT_NOTE, type = DataType.STRING)
	private String note;

	@ForeignKeyColumn(name = ProductRepository.DB_KEY_PRODUCT_SHOP_ID, referencedEntityClass = Shop.class)
	private Shop shop;

	public Product() {

	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param weight
	 * @param postDate
	 * @param brand
	 * @param subCategory
	 * @param originalId
	 * @param shop
	 */
	public Product(final Long id, final String name, final Integer weight,
			final Timestamp postDate, final Brand brand,
			final SubCategory subCategory, final String originalId,
			final String note, final Shop shop) {
		this.id = id;
		this.name = name;
		this.weight = weight;
		this.postDate = postDate;
		this.brand = brand;
		this.subCategory = subCategory;
		this.originalId = originalId;
		this.note = note;
		this.shop = shop;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getWeight() {
		return weight;
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

	public String getOriginalId() {
		return originalId;
	}

	public String getNote() {
		return note;
	}

	public Shop getShop() {
		return shop;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
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

	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

}
