package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.logpie.shopping.tool.model.Brand;

@Repository
public class BrandRepository extends LogpieRepository<Brand> {

	public static final String DB_TABLE_NAME_BRAND = "Brand";
	public static final String DB_KEY_BRAND_ID = "BrandId";
	public static final String DB_KEY_BRAND_NAME = "BrandName";

	@Override
	public Brand mapRow(ResultSet rs, int rowNum) throws SQLException {
		String id = rs.getString(DB_KEY_BRAND_ID);
		String name = rs.getString(DB_KEY_BRAND_NAME);
		return new Brand(id, name);
	}
}
