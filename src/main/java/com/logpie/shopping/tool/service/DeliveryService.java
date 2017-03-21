package com.logpie.shopping.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Long createDelivery(final String deliveryName,
			final Boolean isInternational) {
		logger.trace("createDelivery service is started...");
		if (deliveryName == null || deliveryName.isEmpty()) {
			logger.error("cannot find delivery name");
			return null;
		}
		Delivery delivery = new Delivery(deliveryName, isInternational);
		return repository.insert(delivery);
	}

	public List<Delivery> getAllDeliverys() {
		logger.trace("QueryAllDeliverys service is started...");
		return repository.queryAll(Delivery.class);
	}

	public Delivery getDeliveryById(final Long deliveryId) {
		logger.trace("QueryDeliveryById service is started...");
		if (deliveryId == null) {
			logger.error("cannot find delivery Id");
			return null;
		}
		return repository.queryByID(Delivery.class, deliveryId);
	}
}
