package com.logpie.shopping.tool.model;

import java.sql.Timestamp;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.ForeignEntity;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.annotation.Entity;
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

	@ForeignEntity(name = ProductRepository.DB_KEY_PRODUCT_BRAND_ID, referencedTable = Brand.class)
	private Brand brand;

	@ForeignEntity(name = ProductRepository.DB_KEY_PRODUCT_SUBCATEGORY_ID, referencedTable = SubCategory.class)
	private SubCategory subCategory;

	@ForeignEntity(name = ProductRepository.DB_KEY_PRODUCT_COLOR_ID, referencedTable = Color.class)
	private Color color;

	@ForeignEntity(name = ProductRepository.DB_KEY_PRODUCT_SIZE_ID, referencedTable = Size.class)
	private Size size;

	@Column(name = ProductRepository.DB_KEY_PRODUCT_ORIGINAL_ID, type = DataType.STRING)
	private String originalId;

	@ForeignEntity(name = ProductRepository.DB_KEY_PRODUCT_SHOP_ID, referencedTable = Shop.class)
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
	 * @param color
	 * @param size
	 * @param originalId
	 * @param shop
	 */
	public Product(final Long id, final String name, final Integer weight,
			final Timestamp postDate, final Brand brand,
			final SubCategory subCategory, final Color color, final Size size,
			final String originalId, final Shop shop) {
		this.id = id;
		this.name = name;
		this.weight = weight;
		this.postDate = postDate;
		this.brand = brand;
		this.subCategory = subCategory;
		this.color = color;
		this.size = size;
		this.originalId = originalId;
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

	public Color getColor() {
		return color;
	}

	public Size getSize() {
		return size;
	}

	public String getOriginalId() {
		return originalId;
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

	public void setColor(Color color) {
		this.color = color;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

}
