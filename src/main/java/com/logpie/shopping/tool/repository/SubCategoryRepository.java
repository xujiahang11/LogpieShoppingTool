package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.logpie.shopping.tool.model.SubCategory;
import com.logpie.shopping.tool.service.CategoryService;

@Repository
public class SubCategoryRepository extends LogpieRepository<SubCategory> {
	public static final String DB_TABLE_NAME_SUBCATEGORY = "SubCategory";

	public static final String DB_KEY_SUBCATEGORY_ID = "SubCategoryId";
	public static final String DB_KEY_SUBCATEGORY_NAME = "SubCategoryName";
	public static final String DB_KEY_CATEGORY_ID = "CategoryId";

	@Autowired
	private CategoryService categoryService;

	@Override
	public SubCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
		String id = rs.getString(DB_KEY_SUBCATEGORY_ID);
		String name = rs.getString(DB_KEY_SUBCATEGORY_NAME);
		String categoryId = rs.getString(DB_KEY_CATEGORY_ID);

		return new SubCategory(id, name,
				categoryService.getCategoryById(categoryId));
	}
}
