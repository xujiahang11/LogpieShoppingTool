package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.logpie.shopping.tool.model.Category;
import com.logpie.shopping.tool.model.Shop;
import com.logpie.shopping.tool.model.SubCategory;

@Repository
public class SubCategoryRepository extends LogpieRepository<SubCategory> {
	public static final String DB_TABLE_SUBCATEGORY = "SubCategory";

	public static final String DB_KEY_SUBCATEGORY_ID = "SubCategoryId";
	public static final String DB_KEY_SUBCATEGORY_NAME = "SubCategoryName";
	public static final String DB_KEY_SUBCATEGORY_CATEGORY_ID = "SubCategoryCategoryId";
	public static final String DB_KEY_SUBCATEGORY_SHOP_ID = "SubCategoryShopId";

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ShopRepository shopRepository;

	public List<SubCategory> queryByShopId(final Long shopId)
			throws DataAccessException {
		return super.queryByForeignKey(SubCategory.class,
				DB_KEY_SUBCATEGORY_SHOP_ID, shopId, null);
	}

	public List<SubCategory> queryByCategoryId(final Long categoryId)
			throws DataAccessException {
		return super.queryByForeignKey(SubCategory.class,
				DB_KEY_SUBCATEGORY_CATEGORY_ID, categoryId, null);
	}

	@Override
	public SubCategory mapRow(final ResultSet rs, final int rowNum)
			throws SQLException {
		if (rs == null) {
			return null;
		}
		Long id = rs.getLong(DB_KEY_SUBCATEGORY_ID);
		String name = rs.getString(DB_KEY_SUBCATEGORY_NAME);
		Category category = categoryRepository.mapRow(rs, rowNum);
		Shop shop = shopRepository.mapRow(rs, rowNum);
		return new SubCategory(id, name, category, shop);
	}
}
