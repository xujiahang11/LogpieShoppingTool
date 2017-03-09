package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.logpie.framework.db.util.SQLUtil;
import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Category;
import com.logpie.shopping.tool.model.SubCategory;

@Repository
public class SubCategoryRepository extends LogpieRepository<SubCategory> {
	public static final String DB_TABLE_NAME_SUBCATEGORY = "SubCategory";

	public static final String DB_KEY_SUBCATEGORY_ID = "SubCategoryId";
	public static final String DB_KEY_SUBCATEGORY_NAME = "SubCategoryName";
	public static final String DB_KEY_CATEGORY_ID = "CategoryId";

	@Autowired
	private CategoryRepository categoryRepository;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());

	public List<SubCategory> queryByCategoryId(String[] args) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(DB_TABLE_NAME_SUBCATEGORY, DB_KEY_CATEGORY_ID);
		String sql = SQLUtil.querySQLByKey(SubCategory.class, params);
		logger.debug("'QueryByCategoryId' SQL: " + sql);
		return jdbcTemplate.query(sql, args, this);
	}

	@Override
	public SubCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
		if (rs == null) {
			return null;
		}
		String id = rs.getString(DB_KEY_SUBCATEGORY_ID);
		String name = rs.getString(DB_KEY_SUBCATEGORY_NAME);
		Category category = categoryRepository.mapRow(rs, rowNum);

		return new SubCategory(id, name, category);
	}
}
