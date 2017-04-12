package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.logpie.shopping.tool.model.Delivery;
import com.logpie.shopping.tool.model.Shop;

@Repository
public class DeliveryRepository extends LogpieRepository<Delivery> {
	public static final String DB_TABLE_DELIVERY = "Delivery";

	public static final String DB_KEY_DELIVERY_ID = "DeliveryId";
	public static final String DB_KEY_DELIVERY_NAME = "DeliveryName";
	public static final String DB_KEY_DELIVERY_IS_INTERNATIONAL = "DeliveryIsInternational";
	public static final String DB_KEY_DELIVERY_SHOP_ID = "DeliveryShopId";

	@Autowired
	private ShopRepository shopRepository;

	public List<Delivery> queryByShopId(final Long shopId)
			throws DataAccessException {
		return super.queryByForeignKey(Delivery.class, DB_KEY_DELIVERY_SHOP_ID,
				shopId, null);
	}

	@Override
	public Delivery mapRow(final ResultSet rs, final int rowNum)
			throws SQLException {
		if (rs == null) {
			return null;
		}
		Long id = rs.getLong(DB_KEY_DELIVERY_ID);
		String name = rs.getString(DB_KEY_DELIVERY_NAME);
		Boolean isInternational = rs
				.getBoolean(DB_KEY_DELIVERY_IS_INTERNATIONAL);
		Shop shop = shopRepository.mapRow(rs, rowNum);
		return new Delivery(id, name, isInternational, shop);
	}

}
