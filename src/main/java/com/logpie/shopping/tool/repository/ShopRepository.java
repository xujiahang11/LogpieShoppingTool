package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.logpie.framework.db.basic.Parameter;
import com.logpie.framework.db.basic.WhereParam;
import com.logpie.framework.db.repository.JDBCTemplateRepository;
import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Shop;

@Repository
public class ShopRepository extends JDBCTemplateRepository<Shop> {

	public static final Integer PAGE_SIZE = 20;

	public static final String DB_TABLE_SHOP = "Shop";

	public static final String DB_KEY_SHOP_ID = "shopId";
	public static final String DB_KEY_SHOP_NAME = "shopName";
	public static final String DB_KEY_SHOP_SMALL_LOGO = "sLogo";
	public static final String DB_KEY_SHOP_LOGO = "logo";
	public static final String DB_KEY_SHOP_REGISTER_TIME = "shopRegisterTime";
	public static final String DB_KEY_SHOP_PATH = "shopPath";
	public static final String DB_KEY_SHOP_EXP = "shopExp";

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());

	public ShopRepository() {
		super(Shop.class);
	}

	public Shop queryByPath(final String path) throws DataAccessException {
		Parameter param = new WhereParam(Shop.class, DB_KEY_SHOP_PATH, path);
		List<Shop> res = (List<Shop>) super.queryBy(param);
		if (res.size() != 1) {
			logger.error("the number of query object is not correct");
			return null;
		}
		return res.get(0);
	}

	@Override
	public Shop mapRow(ResultSet rs, int rowNum) throws SQLException {
		Shop shop = new Shop();
		shop.setId(rs.getLong(DB_KEY_SHOP_ID));
		shop.setName(rs.getString(DB_KEY_SHOP_NAME));
		shop.setSmallLogo(rs.getString(DB_KEY_SHOP_SMALL_LOGO));
		shop.setLogo(rs.getString(DB_KEY_SHOP_LOGO));
		shop.setDate(rs.getTimestamp(DB_KEY_SHOP_REGISTER_TIME));
		shop.setPath(rs.getString(DB_KEY_SHOP_PATH));
		shop.setExp(rs.getInt(DB_KEY_SHOP_EXP));

		return shop;
	}

}
