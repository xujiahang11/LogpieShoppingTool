package com.logpie.shopping.tool.repository;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.logpie.dba.api.basic.Page;
import com.logpie.dba.api.basic.PageRequest;
import com.logpie.dba.api.basic.Pageable;
import com.logpie.dba.api.basic.Parameter;
import com.logpie.dba.api.basic.WhereParam;
import com.logpie.dba.api.repository.JDBCTemplateRepository;
import com.logpie.shopping.tool.model.Brand;

@Repository
public class BrandRepository extends JDBCTemplateRepository<Brand> {

	public static final Integer PAGE_SIZE = 20;

	public static final String DB_TABLE_BRAND = "Brand";

	public static final String DB_KEY_BRAND_ID = "brandId";
	public static final String DB_KEY_BRAND_NAME = "brandName";
	public static final String DB_KEY_BRAND_SHOP_ID = "brandShopId";

	@Autowired
	private ShopRepository shopRepository;

	public BrandRepository() {
		super(Brand.class);
	}

	public Page<Brand> queryByShopId(final int pageNumber, final BigInteger shopId)
			throws DataAccessException {
		Parameter param = new WhereParam(Brand.class, DB_KEY_BRAND_SHOP_ID,
				shopId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE);

		return super.queryBy(request, param);
	}

	public List<Brand> queryByShopId(final BigInteger shopId)
			throws DataAccessException {
		Parameter param = new WhereParam(Brand.class, DB_KEY_BRAND_SHOP_ID,
				shopId);

		return (List<Brand>) super.queryBy(param);
	}
}
