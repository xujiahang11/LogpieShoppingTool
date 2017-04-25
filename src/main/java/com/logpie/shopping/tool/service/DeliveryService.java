package com.logpie.shopping.tool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.logpie.framework.db.basic.Page;
import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Delivery;
import com.logpie.shopping.tool.repository.DeliveryRepository;

@Service
public class DeliveryService {
	@Autowired
	private DeliveryRepository repository;
	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());

	public Long createDelivery(final Delivery delivery) {
		logger.trace("createDelivery service is started...");
		Assert.notNull(delivery, "Delivery must not be null");

		try {
			return repository.insert(delivery);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateDelivery(final Delivery delivery) {
		logger.trace("updateDelivery service is started...");
		Assert.notNull(delivery, "Delivery must not be null");

		repository.update(delivery);
	}

	public Delivery getDeliveryById(final Long id) {
		logger.trace("QueryDeliveryById service is started...");
		Assert.notNull(id, "Id must not be null");

		return repository.queryOne(id);
	}

	public Page<Delivery> getDeliverysByShopId(final int pageNumber,
			final Long shopId) {
		logger.trace("QueryDeliverysByShopId service is started...");
		Assert.notNull(shopId, "Shop id must not be null");

		return repository.queryByShopId(pageNumber, shopId);
	}
}
