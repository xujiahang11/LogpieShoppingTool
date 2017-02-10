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
			.getLogger(BrandService.class);

	public void createBrand(final String brandName) {
		if (brandName == null || brandName == "") {
			return;
		}
		Brand brand = new Brand(brandName);
		repository.create(brand);
	}

	public List<Brand> getAllBrands() {
		logger.trace("QueryAllBrands service started...");
		return repository.queryAll(Brand.class);
	}

	public Brand getBrandById(final String brandId) {
		String[] args = new String[1];
		args[0] = brandId;
		return repository.queryByPrimaryKey(Brand.class, args);
	}
}
