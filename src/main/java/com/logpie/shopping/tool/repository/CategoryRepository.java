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

	public Page<Category> queryByShopId(final int pageNumber, final BigInteger shopId)
			throws DataAccessException {
		Parameter param = new WhereParam(Category.class,
				DB_KEY_CATEGORY_SHOP_ID, shopId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE);

		return super.queryBy(request, param);
	}

	public List<Category> queryByShopId(final BigInteger shopId)
			throws DataAccessException {
		Parameter param = new WhereParam(Category.class,
				DB_KEY_CATEGORY_SHOP_ID, shopId);

		return (List<Category>) super.queryBy(param);
	}

}
