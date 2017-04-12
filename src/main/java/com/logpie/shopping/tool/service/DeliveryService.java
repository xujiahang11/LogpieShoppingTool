package com.logpie.shopping.tool.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

	public Long createDelivery(final Delivery delivery) {
		logger.trace("createDelivery service is started...");
		if (delivery == null) {
			logger.error("cannot find delivery");
			return null;
		}
		try {
			return repository.insert(delivery);
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateDelivery(final Delivery delivery) {
		logger.trace("updateDelivery service is started...");
		if (delivery == null) {
			logger.error("cannot find delivery");
			return;
		}
		repository.update(delivery);
	}

	public List<Delivery> getAllDeliverys() {
		logger.trace("QueryAllDeliverys service is started...");
		return repository.queryAll(Delivery.class, null);
	}

	public Delivery getDeliveryById(final Long id) {
		logger.trace("QueryDeliveryById service is started...");
		if (id == null) {
			logger.error("cannot find delivery id");
			return null;
		}
		return repository.queryById(Delivery.class, id);
	}

	public List<Delivery> getDeliverysByShopId(final Long shopId) {
		logger.trace("QueryDeliverysByShopId service is started...");
		if (shopId == null) {
			logger.error("cannot find shop Id");
			return null;
		}
		return repository.queryByShopId(shopId);
	}
}
