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
import com.logpie.shopping.tool.model.Category;

@Repository
public class CategoryRepository extends JDBCTemplateRepository<Category> {

	public static final Integer PAGE_SIZE = 20;

	public static final String DB_TABLE_CATEGORY = "Category";

	public static final String DB_KEY_CATEGORY_ID = "categoryId";
	public static final String DB_KEY_CATEGORY_NAME = "categoryName";
	public static final String DB_KEY_CATEGORY_SHOP_ID = "categoryShopId";

	@Autowired
	private ShopRepository shopRepository;

	public CategoryRepository() {
		super(Category.class);
	}

	public Page<Category> queryByShopId(final int pageNumber, final Long shopId)
			throws DataAccessException {
		Parameter param = new WhereParam(Category.class,
				DB_KEY_CATEGORY_SHOP_ID, shopId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE);

		return super.queryBy(request, param);
	}

	@Override
	public Category mapRow(final ResultSet rs, final int rowNum)
			throws SQLException {
		Category category = new Category();
		category.setId(rs.getLong(DB_KEY_CATEGORY_ID));
		category.setName(rs.getString(DB_KEY_CATEGORY_NAME));
		category.setShop(shopRepository.mapRow(rs, rowNum));
		return category;
	}

}
