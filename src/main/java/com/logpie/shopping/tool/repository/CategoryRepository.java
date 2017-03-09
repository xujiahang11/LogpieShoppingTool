package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.logpie.shopping.tool.model.Category;

@Repository
public class CategoryRepository extends LogpieRepository<Category> {

	public static final String DB_TABLE_NAME_CATEGORY = "Category";

	public static final String DB_KEY_CATEGORY_ID = "CategoryId";
	public static final String DB_KEY_CATEGORY_NAME = "CategoryName";

	@Override
	public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
		if (rs == null) {
			return null;
		}

		String id = rs.getString(DB_KEY_CATEGORY_ID);
		String name = rs.getString(DB_KEY_CATEGORY_NAME);
		return new Category(id, name);
	}

}