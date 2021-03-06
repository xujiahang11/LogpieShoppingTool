package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.logpie.dba.api.basic.Parameter;
import com.logpie.dba.api.basic.WhereParam;
import com.logpie.dba.api.repository.JDBCTemplateRepository;
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
}
