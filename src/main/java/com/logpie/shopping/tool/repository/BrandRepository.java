package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.logpie.shopping.tool.model.Brand;

@Repository
public class BrandRepository implements LogpieRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void create(String brandName) {
		jdbcTemplate
				.update("insert into Brand(BrandName) values(?)", brandName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Brand queryByPrimaryKey(String[] args) {
		return jdbcTemplate.queryForObject(
				"select * from Brand where brandId = ?", args,
				new BrandRowMapper());
	}

	@Override
	public List<Brand> queryAll() {
		return jdbcTemplate.query("select * from Brand", new BrandRowMapper());
	}

	/**
	 * implement rowMapper for brand
	 * 
	 * @author xujiahang
	 *
	 */
	class BrandRowMapper implements RowMapper<Brand> {

		@Override
		public Brand mapRow(ResultSet rs, int rowNum) throws SQLException {
			String id = rs.getString(Brand.DB_KEY_BRAND_ID);
			String name = rs.getString(Brand.DB_KEY_BRAND_NAME);
			return new Brand(id, name);
		}

	}
}
