package com.logpie.shopping.tool.repository;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.logpie.dba.api.basic.Parameter;
import com.logpie.dba.api.basic.WhereParam;
import com.logpie.dba.api.repository.JDBCTemplateRepository;
import com.logpie.shopping.tool.model.ProductConfig;

@Repository
public class ProductConfigRepository extends
		JDBCTemplateRepository<ProductConfig> {

	public static final String DB_TABLE_SIZE = "ProductConfig";

	public static final String DB_KEY_CONFIG_ID = "configId";
	public static final String DB_KEY_CONFIG_DESC = "configDescription";
	public static final String DB_KEY_CONFIG_PRICE = "configPrice";
	public static final String DB_KEY_CONFIG_WEIGHT = "configWeight";
	public static final String DB_KEY_CONFIG_ITEM_NUMBER = "configItemNumber";
	public static final String DB_KEY_CONFIG_PRODUCT_ID = "configProductId";

	@Autowired
	private ProductRepository productRepository;

	public ProductConfigRepository() {
		super(ProductConfig.class);
	}

	public List<ProductConfig> queryByProductId(final BigInteger productId)
			throws DataAccessException {
		Parameter param = new WhereParam(ProductConfig.class,
				DB_KEY_CONFIG_PRODUCT_ID, productId);
		return (List<ProductConfig>) super.queryBy(param);
	}
}
