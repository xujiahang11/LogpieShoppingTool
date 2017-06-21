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

	@ForeignKeyColumn(name = ProductConfigRepository.DB_KEY_CONFIG_PRODUCT_ID, referencedEntityClass = Product.class)
	private Product product;

	public ProductConfig() {

	}

	public ProductConfig(Long id, String desc, Product product) {
		this.id = id;
		this.desc = desc;
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}

	public Product getProduct() {
		return product;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
