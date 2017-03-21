package com.logpie.shopping.tool.model;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.annotation.Table;
import com.logpie.framework.db.util.LogpieModel;
import com.logpie.shopping.tool.repository.DeliveryRepository;

@Table(name = DeliveryRepository.DB_TABLE_DELIVERY)
public class Delivery extends LogpieModel {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = DeliveryRepository.DB_KEY_DELIVERY_ID, type = DataType.LONG)
	private Long deliveryId;

	@Column(name = DeliveryRepository.DB_KEY_DELIVERY_NAME, type = DataType.STRING)
	private String deliveryName;

	@Column(name = DeliveryRepository.DB_KEY_IS_INTERNATIONAL, type = DataType.BOOLEAN)
	private Boolean isInternational;

	/**
	 * Constructor for creating a delivery
	 * 
	 * @param deliveryName
	 * @param isInternational
	 */
	public Delivery(String deliveryName, Boolean isInternational) {
		this(null, deliveryName, isInternational);
	}

	/**
	 * 
	 * @param deliveryId
	 * @param deliveryName
	 * @param isInternational
	 */
	public Delivery(Long deliveryId, String deliveryName,
			Boolean isInternational) {
		this.deliveryId = deliveryId;
		this.deliveryName = deliveryName;
		this.isInternational = isInternational;
	}

	public Long getDeliveryId() {
		return deliveryId;
	}

	public String getDeliveryName() {
		return deliveryName;
	}

	public Boolean getIsInternational() {
		return isInternational;
	}

	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	public void setIsInternational(Boolean isInternational) {
		this.isInternational = isInternational;
	}

}
