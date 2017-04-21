package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.logpie.framework.db.basic.Page;
import com.logpie.framework.db.basic.PageRequest;
import com.logpie.framework.db.basic.Pageable;
import com.logpie.framework.db.basic.Parameter;
import com.logpie.framework.db.basic.WhereParam;
import com.logpie.framework.db.repository.JDBCTemplateRepository;
import com.logpie.shopping.tool.model.Delivery;
import com.logpie.shopping.tool.model.Shop;

@Repository
public class DeliveryRepository extends JDBCTemplateRepository<Delivery> {

	public static final Integer PAGE_SIZE = 20;

	public static final String DB_TABLE_DELIVERY = "Delivery";

	public static final String DB_KEY_DELIVERY_ID = "DeliveryId";
	public static final String DB_KEY_DELIVERY_NAME = "DeliveryName";
	public static final String DB_KEY_DELIVERY_IS_INTERNATIONAL = "DeliveryIsInternational";
	public static final String DB_KEY_DELIVERY_SHOP_ID = "DeliveryShopId";

	@Autowired
	private ShopRepository shopRepository;

	public DeliveryRepository() {
		super(Delivery.class);
	}

	public Page<Delivery> queryByShopId(final int pageNumber, final Long shopId)
			throws DataAccessException {
		Parameter param = new WhereParam(Delivery.class,
				DB_KEY_DELIVERY_SHOP_ID, shopId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE);

		return super.queryBy(request, param);
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
