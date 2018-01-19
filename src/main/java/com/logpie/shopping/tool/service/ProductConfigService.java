package com.logpie.shopping.tool.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.ProductConfig;
import com.logpie.shopping.tool.repository.ProductConfigRepository;

@Service
public class ProductConfigService {

	@Autowired
	private ProductConfigRepository repository;
	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());

	public ProductConfig createProductConfig(final ProductConfig config) {
		logger.trace("createProductConfig service is started...");
		Assert.notNull(config, "Config must not be null");

		try {
			return repository.insert(config);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateProductConfig(final ProductConfig config) {
		logger.trace("updateProductConfig service is started...");
		Assert.notNull(config, "Config must not be null");

		repository.update(config);
	}

	public ProductConfig getProductConfigById(final BigInteger id) {
		logger.trace("QueryProductConfigById service is started...");
		Assert.notNull(id, "Id must not be null");

		return repository.queryOne(id);
	}

	public List<ProductConfig> getProductConfigsByProductId(final BigInteger productId) {
		logger.trace("QueryProductConfigsByProductId service is started...");
		Assert.notNull(productId, "Product id must not be null");

		return repository.queryByProductId(productId);
	}
}
