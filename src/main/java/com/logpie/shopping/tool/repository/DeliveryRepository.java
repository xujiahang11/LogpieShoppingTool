package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.logpie.shopping.tool.model.Delivery;

@Repository
public class DeliveryRepository extends LogpieRepository<Delivery> {
	public static final String DB_TABLE_DELIVERY = "Delivery";
	public static final String DB_KEY_DELIVERY_ID = "DeliveryId";
	public static final String DB_KEY_DELIVERY_NAME = "DeliveryName";
	public static final String DB_KEY_IS_INTERNATIONAL = "DeliveryIsInternational";

	@Override
	public Delivery mapRow(final ResultSet rs, final int rowNum)
			throws SQLException {
		if (rs == null) {
			return null;
		}
		Long deliveryId = rs.getLong(DB_KEY_DELIVERY_ID);
		String deliveryName = rs.getString(DB_KEY_DELIVERY_NAME);
		Boolean isInternational = rs.getBoolean(DB_KEY_IS_INTERNATIONAL);
		return new Delivery(deliveryId, deliveryName, isInternational);
	}

}
