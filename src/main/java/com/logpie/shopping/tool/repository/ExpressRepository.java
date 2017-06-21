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
import com.logpie.shopping.tool.model.Express;

@Repository
public class ExpressRepository extends JDBCTemplateRepository<Express> {

	public static final Integer PAGE_SIZE = 20;

	public static final String DB_TABLE_EXPRESS = "Express";

	public static final String DB_KEY_EXPRESS_ID = "id";
	public static final String DB_KEY_EXPRESS_NAME = "name";
	public static final String DB_KEY_EXPRESS_IS_INTERNATIONAL = "isInternational";
	public static final String DB_KEY_EXPRESS_SHOP_ID = "shopId";

	@Autowired
	private ShopRepository shopRepository;

	public ExpressRepository() {
		super(Express.class);
	}

	public Page<Express> queryByShopId(final int pageNumber, final Long shopId)
			throws DataAccessException {
		Parameter param = new WhereParam(Express.class, DB_KEY_EXPRESS_SHOP_ID,
				shopId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE);

		return super.queryBy(request, param);
	}

	@Override
	public Express mapRow(final ResultSet rs, final int rowNum)
			throws SQLException {
		Express express = new Express();
		express.setId(rs.getLong(DB_KEY_EXPRESS_ID));
		express.setName(rs.getString(DB_KEY_EXPRESS_NAME));
		express.setIsInternational(rs
				.getBoolean(DB_KEY_EXPRESS_IS_INTERNATIONAL));
		express.setShop(shopRepository.mapRow(rs, rowNum));
		return express;
	}

}
