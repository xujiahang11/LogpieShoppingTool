package com.logpie.shopping.tool.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

	public Long createBrand(final Brand brand) {
		logger.trace("createBrand service is started...");
		if (brand == null) {
			logger.error("cannot find brand");
			return null;
		}
		try {
			return repository.insert(brand);
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateBrand(final Brand brand) {
		logger.trace("updateBrand service is started...");
		if (brand == null) {
			logger.error("cannot find brand");
			return;
		}
		repository.update(brand);
	}

	public List<Brand> getAllBrands() {
		logger.trace("QueryAllBrands service is started...");
		return repository.queryAll(Brand.class, null);
	}

	public Brand getBrandById(final Long id) {
		logger.trace("QueryBrandById service is started...");
		if (id == null) {
			logger.error("cannot find brand id");
			return null;
		}
		return repository.queryById(Brand.class, id);
	}

	public List<Brand> getBrandsByShopId(final Long shopId) {
		logger.trace("QueryBrandsByShopId service is started...");
		if (shopId == null) {
			logger.error("cannot find shop id");
			return null;
		}
		return repository.queryByShopId(shopId);
	}
}
