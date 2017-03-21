package com.logpie.shopping.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Long createBrand(final String brandName) {
		logger.trace("createBrand service is started...");
		if (brandName == null || brandName.isEmpty()) {
			logger.error("cannot find brand name");
			return null;
		}
		Brand brand = new Brand(brandName);
		return repository.insert(brand);
	}

	public List<Brand> getAllBrands() {
		logger.trace("QueryAllBrands service is started...");
		return repository.queryAll(Brand.class);
	}

	public Brand getBrandById(final Long brandId) {
		logger.trace("QueryBrandById service is started...");
		if (brandId == null) {
			logger.error("cannot find brand Id");
			return null;
		}
		return repository.queryByID(Brand.class, brandId);
	}
}
