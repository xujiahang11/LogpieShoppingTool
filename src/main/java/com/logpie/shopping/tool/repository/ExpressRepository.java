package com.logpie.shopping.tool.repository;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.logpie.dba.api.basic.Page;
import com.logpie.dba.api.basic.PageRequest;
import com.logpie.dba.api.basic.Pageable;
import com.logpie.dba.api.basic.Parameter;
import com.logpie.dba.api.basic.WhereParam;
import com.logpie.dba.api.repository.JDBCTemplateRepository;
import com.logpie.shopping.tool.model.Express;

@Repository
public class ExpressRepository extends JDBCTemplateRepository<Express> {

	public static final Integer PAGE_SIZE = 20;

	public static final String DB_TABLE_EXPRESS = "Express";

	public static final String DB_KEY_EXPRESS_ID = "expressId";
	public static final String DB_KEY_EXPRESS_NAME = "expressName";
	public static final String DB_KEY_EXPRESS_IS_INTERNATIONAL = "expressIsInternational";
	public static final String DB_KEY_EXPRESS_SHOP_ID = "expressShopId";

	@Autowired
	private ShopRepository shopRepository;

	public ExpressRepository() {
		super(Express.class);
	}

	public Page<Express> queryByShopId(final int pageNumber, final BigInteger shopId)
			throws DataAccessException {
		Parameter param = new WhereParam(Express.class, DB_KEY_EXPRESS_SHOP_ID,
				shopId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE);

		return super.queryBy(request, param);
	}
}
