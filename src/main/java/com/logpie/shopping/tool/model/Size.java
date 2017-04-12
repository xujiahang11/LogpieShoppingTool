package com.logpie.shopping.tool.model;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.ForeignEntity;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.annotation.Table;
import com.logpie.framework.db.basic.Model;
import com.logpie.shopping.tool.repository.SizeRepository;

@Table(name = SizeRepository.DB_TABLE_SIZE)
public class Size extends Model {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = SizeRepository.DB_KEY_SIZE_ID, type = DataType.LONG)
	private Long id;

	@Column(name = SizeRepository.DB_KEY_SIZE_NAME, type = DataType.STRING)
	private String name;

	@ForeignEntity(name = SizeRepository.DB_KEY_SIZE_SHOP_ID, referencedTable = Shop.class)
	private Shop shop;

	public Size() {

	}

	public Size(Long id, String name, Shop shop) {
		this.id = id;
		this.name = name;
		this.shop = shop;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
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

	public void setShop(Shop shop) {
		this.shop = shop;
	}
}
