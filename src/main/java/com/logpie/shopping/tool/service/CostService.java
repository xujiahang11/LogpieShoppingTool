package com.logpie.shopping.tool.service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Cost;
import com.logpie.shopping.tool.repository.CostRepository;

@Service
public class CostService {
	@Autowired
	private CostRepository repository;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());

	public Cost createCost(final Cost cost) {
		logger.trace("createCost service is started...");
		Assert.notNull(cost, "Cost must not be null");

		try {
			return repository.insert(cost);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateCost(final Cost cost) {
		logger.trace("updateCost service is started...");
		Assert.notNull(cost, "Cost must not be null");

		repository.update(cost);
	}

	public Cost getCostById(final BigInteger id) {
		logger.trace("QueryCostById service is started...");
		Assert.notNull(id, "Id must not be null");

		return repository.queryOne(id);
	}

	// TODO 设计可能用到的按时间查询的方法
	public List<Cost> getCostsByPeriod(final Timestamp time,
			final Long duration, final BigInteger shopId) {
		logger.trace("queryCostsByPeriod service is started...");
		Assert.notNull(shopId, "shop id must not be null");

		return repository.queryByPeriod();
	}
}
