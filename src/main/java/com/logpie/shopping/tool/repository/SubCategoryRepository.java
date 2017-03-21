package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.logpie.shopping.tool.model.Category;
import com.logpie.shopping.tool.model.SubCategory;

@Repository
public class SubCategoryRepository extends LogpieRepository<SubCategory> {
	public static final String DB_TABLE_SUBCATEGORY = "SubCategory";

	public static final String DB_KEY_SUBCATEGORY_ID = "SubCategoryId";
	public static final String DB_KEY_SUBCATEGORY_NAME = "SubCategoryName";
	public static final String DB_KEY_CATEGORY_ID = "CategoryId";

	@Autowired
	private CategoryRepository categoryRepository;

	public List<SubCategory> queryByCategoryId(Long arg) {
		return super.queryByForeignKey(SubCategory.class, DB_KEY_CATEGORY_ID,
				arg);
	}

	@Override
	public SubCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
		if (rs == null) {
			return null;
		}
		Long id = rs.getLong(DB_KEY_SUBCATEGORY_ID);
		String name = rs.getString(DB_KEY_SUBCATEGORY_NAME);
		Category category = categoryRepository.mapRow(rs, rowNum);

		return new SubCategory(id, name, category);
	}
}
