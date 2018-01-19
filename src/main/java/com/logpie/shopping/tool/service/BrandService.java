package com.logpie.shopping.tool.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.logpie.dba.api.basic.Page;
import com.logpie.framework.log.annotation.LogEnvironment;
import com.logpie.framework.log.annotation.LogEnvironment.LogLevel;
import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Brand;
import com.logpie.shopping.tool.repository.BrandRepository;

@Service
@LogEnvironment(classLevel = LogLevel.TRACE)
public class BrandService {
	@Autowired
	private BrandRepository repository;
	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());

	public Brand createBrand(final Brand brand) {
		logger.trace("createBrand service is started...");
		Assert.notNull(brand, "Brand must not be null");

		try {
			return repository.insert(brand);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateBrand(final Brand brand) {
		logger.trace("updateBrand service is started...");
		Assert.notNull(brand, "Brand must not be null");

		repository.update(brand);
	}

	public Brand getBrandById(final BigInteger id) {
		logger.trace("QueryBrandById service is started...");
		Assert.notNull(id, "Id must not be null");

		return repository.queryOne(id); // TODO
	}

	public Page<Brand> getBrandsByShopId(final int pageNumber, final BigInteger shopId) {
		logger.trace("QueryBrandsByShopId service is started...");
		Assert.notNull(shopId, "Shop id must not be null");

		return repository.queryByShopId(pageNumber, shopId);
	}

	public List<Brand> getBrandsByShopId(final BigInteger shopId) {
		logger.trace("QueryBrandsByShopId service is started...");
		Assert.notNull(shopId, "Shop id must not be null");

		return repository.queryByShopId(shopId);
	}
}
