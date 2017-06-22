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
import com.logpie.shopping.tool.model.ProductConfig;

@Repository
public class ProductConfigRepository extends
		JDBCTemplateRepository<ProductConfig> {

	public static final String DB_TABLE_SIZE = "ProductConfig";

	public static final String DB_KEY_CONFIG_ID = "configId";
	public static final String DB_KEY_CONFIG_DESC = "configDescription";
	public static final String DB_KEY_CONFIG_PRODUCT_ID = "configProductId";

	@Autowired
	private ProductRepository productRepository;

	public ProductConfigRepository() {
		super(ProductConfig.class);
	}

	public List<ProductConfig> queryByProductId(final Long productId)
			throws DataAccessException {
		Parameter param = new WhereParam(ProductConfig.class,
				DB_KEY_CONFIG_PRODUCT_ID, productId);
		return (List<ProductConfig>) super.queryBy(param);
	}

	@Override
	public ProductConfig mapRow(final ResultSet rs, final int rowNum)
			throws SQLException {
		ProductConfig config = new ProductConfig();
		config.setId(rs.getLong(DB_KEY_CONFIG_ID));
		config.setDesc(rs.getString(DB_KEY_CONFIG_DESC));
		config.setProduct(productRepository.mapRow(rs, rowNum));
		return config;
	}

}
