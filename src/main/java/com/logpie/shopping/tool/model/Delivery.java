package com.logpie.shopping.tool.model;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.ForeignEntity;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.annotation.Table;
import com.logpie.framework.db.basic.Model;
import com.logpie.shopping.tool.repository.DeliveryRepository;

@Table(name = DeliveryRepository.DB_TABLE_DELIVERY)
public class Delivery extends Model {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = DeliveryRepository.DB_KEY_DELIVERY_ID, type = DataType.LONG)
	private Long id;

	@Column(name = DeliveryRepository.DB_KEY_DELIVERY_NAME, type = DataType.STRING)
	private String name;

	@Column(name = DeliveryRepository.DB_KEY_DELIVERY_IS_INTERNATIONAL, type = DataType.BOOLEAN)
	private Boolean isInternational;

	@ForeignEntity(name = DeliveryRepository.DB_KEY_DELIVERY_SHOP_ID, referencedTable = Shop.class)
	private Shop shop;

	public Delivery() {

	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param isInternational
	 * @param shop
	 */
	public Delivery(final Long id, final String name,
			final Boolean isInternational, final Shop shop) {
		this.id = id;
		this.name = name;
		this.isInternational = isInternational;
		this.shop = shop;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Boolean getIsInternational() {
		return isInternational;
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

	public void setIsInternational(Boolean isInternational) {
		this.isInternational = isInternational;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
}
