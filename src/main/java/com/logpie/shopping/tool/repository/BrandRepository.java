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
import com.logpie.shopping.tool.model.Brand;
import com.logpie.shopping.tool.model.Shop;

@Repository
public class BrandRepository extends JDBCTemplateRepository<Brand> {

	public static final Integer PAGE_SIZE = 20;

	public static final String DB_TABLE_BRAND = "Brand";

	public static final String DB_KEY_BRAND_ID = "BrandId";
	public static final String DB_KEY_BRAND_NAME = "BrandName";
	public static final String DB_KEY_BRAND_SHOP_ID = "BrandShopId";

	@Autowired
	private ShopRepository shopRepository;

	public BrandRepository() {
		super(Brand.class);
	}

	public Page<Brand> queryByShopId(final int pageNumber, final Long shopId)
			throws DataAccessException {
		Parameter param = new WhereParam(Brand.class, DB_KEY_BRAND_SHOP_ID,
				shopId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE);

		return super.queryBy(request, param);
	}

	@Override
	public Brand mapRow(final ResultSet rs, final int rowNum)
			throws SQLException {
		if (rs == null) {
			return null;
		}

		Long id = rs.getLong(DB_KEY_BRAND_ID);
		String name = rs.getString(DB_KEY_BRAND_NAME);
		Shop shop = shopRepository.mapRow(rs, rowNum);
		return new Brand(id, name, shop);
	}
}
