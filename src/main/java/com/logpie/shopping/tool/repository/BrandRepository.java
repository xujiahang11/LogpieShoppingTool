package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.logpie.shopping.tool.model.Brand;
import com.logpie.shopping.tool.model.Shop;

@Repository
public class BrandRepository extends LogpieRepository<Brand> {

	public static final String DB_TABLE_BRAND = "Brand";

	public static final String DB_KEY_BRAND_ID = "BrandId";
	public static final String DB_KEY_BRAND_NAME = "BrandName";
	public static final String DB_KEY_BRAND_SHOP_ID = "BrandShopId";

	@Autowired
	private ShopRepository shopRepository;

	public List<Brand> queryByShopId(final Long shopId)
			throws DataAccessException {
		return super.queryByForeignKey(Brand.class, DB_KEY_BRAND_SHOP_ID,
				shopId, null);
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
