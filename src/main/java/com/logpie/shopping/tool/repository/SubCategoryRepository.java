package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.logpie.framework.db.basic.Parameter;
import com.logpie.framework.db.basic.WhereParam;
import com.logpie.framework.db.repository.JDBCTemplateRepository;
import com.logpie.shopping.tool.model.SubCategory;

@Repository
public class SubCategoryRepository extends JDBCTemplateRepository<SubCategory> {

	public static final String DB_TABLE_SUBCATEGORY = "SubCategory";

	public static final String DB_KEY_SUBCATEGORY_ID = "subcategoryId";
	public static final String DB_KEY_SUBCATEGORY_NAME = "subcategoryName";
	public static final String DB_KEY_SUBCATEGORY_CATEGORY_ID = "subcategoryCategoryId";

	@Autowired
	private CategoryRepository categoryRepository;

	public SubCategoryRepository() {
		super(SubCategory.class);
	}

	public List<SubCategory> queryByCategoryId(final Long categoryId)
			throws DataAccessException {
		Parameter param = new WhereParam(SubCategory.class,
				DB_KEY_SUBCATEGORY_CATEGORY_ID, categoryId);
		return (List<SubCategory>) super.queryBy(param);
	}
}
