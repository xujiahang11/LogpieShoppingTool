package com.logpie.shopping.tool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.logpie.dba.api.basic.Page;
import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Express;
import com.logpie.shopping.tool.repository.ExpressRepository;

import java.math.BigInteger;

@Service
public class ExpressService {
	@Autowired
	private ExpressRepository repository;
	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());

	public Express createExpress(final Express express) {
		logger.trace("createExpress service is started...");
		Assert.notNull(express, "Express must not be null");

		try {
			return repository.insert(express);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateExpress(final Express express) {
		logger.trace("updateExpress service is started...");
		Assert.notNull(express, "Express must not be null");

		repository.update(express);
	}

	public Express getExpressById(final BigInteger id) {
		logger.trace("QueryExpressById service is started...");
		Assert.notNull(id, "Id must not be null");

		return repository.queryOne(id);
	}

	public Page<Express> getExpressByShopId(final int pageNumber,
			final BigInteger shopId) {
		logger.trace("QueryExpressByShopId service is started...");
		Assert.notNull(shopId, "Shop id must not be null");

		return repository.queryByShopId(pageNumber, shopId);
	}
}
