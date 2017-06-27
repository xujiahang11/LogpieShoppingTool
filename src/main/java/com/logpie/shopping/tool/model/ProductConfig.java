package com.logpie.shopping.tool.model;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.Entity;
import com.logpie.framework.db.annotation.ForeignKeyColumn;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.basic.Model;
import com.logpie.shopping.tool.repository.ProductConfigRepository;

@Entity(name = ProductConfigRepository.DB_TABLE_SIZE)
public class ProductConfig extends Model {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = ProductConfigRepository.DB_KEY_CONFIG_ID, type = DataType.LONG)
	private Long id;

	@Column(name = ProductConfigRepository.DB_KEY_CONFIG_DESC, type = DataType.STRING)
	private String desc;

	@Column(name = ProductConfigRepository.DB_KEY_CONFIG_PRICE, type = DataType.FLOAT)
	private Float price;

	@ForeignKeyColumn(name = ProductConfigRepository.DB_KEY_CONFIG_PRODUCT_ID, referencedEntityClass = Product.class)
	private Product product;

	private static final String ONE_SIZE = "ONE_SIZE";

	public ProductConfig() {

	}

	public ProductConfig(Long id, String desc, Float price, Product product) {
		this.id = id;
		setDesc(desc);
		this.price = price;
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}

	public Float getPrice() {
		return price;
	}

	public Product getProduct() {
		return product;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDesc(String desc) {
		this.desc = (desc == null || desc.equals("")) ? ONE_SIZE : desc;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
