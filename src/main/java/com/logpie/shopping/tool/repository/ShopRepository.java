package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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

	public static final String DB_KEY_SHOP_ID = "ShopId";
	public static final String DB_KEY_SHOP_NAME = "ShopName";
	public static final String DB_KEY_SHOP_SMALL_LOGO = "ShopSmallLogo";
	public static final String DB_KEY_SHOP_LARGE_LOGO = "ShopLargeLogo";
	public static final String DB_KEY_SHOP_DATE = "ShopDate";
	public static final String DB_KEY_SHOP_PATH = "ShopPath";
	public static final String DB_KEY_SHOP_EXP = "ShopExp";

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
		Long shopId = rs.getLong(DB_KEY_SHOP_ID);
		String shopName = rs.getString(DB_KEY_SHOP_NAME);
		String shopSmallLogo = rs.getString(DB_KEY_SHOP_SMALL_LOGO);
		String shopLargeLogo = rs.getString(DB_KEY_SHOP_LARGE_LOGO);
		Timestamp shopDate = rs.getTimestamp(DB_KEY_SHOP_DATE);
		String shopPath = rs.getString(DB_KEY_SHOP_PATH);
		Integer shopExp = rs.getInt(DB_KEY_SHOP_EXP);

		return new Shop(shopId, shopName, shopSmallLogo, shopLargeLogo,
				shopDate, shopPath, shopExp);
	}

}
