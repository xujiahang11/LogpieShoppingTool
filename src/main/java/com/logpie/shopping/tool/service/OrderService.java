package com.logpie.shopping.tool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.logpie.dba.api.basic.Page;
import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Order;
import com.logpie.shopping.tool.model.Order.OrderStatus;
import com.logpie.shopping.tool.repository.OrderRepository;

import java.math.BigInteger;

@Service
public class OrderService {
	@Autowired
	private OrderRepository repository;
	@Autowired
	private ProductService productService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private PackageService packageService;
	@Autowired
	private ExpressService deliveryService;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());

	public Order createOrder(final Order order) {
		logger.trace("createOrder service is started...");
		Assert.notNull(order, "Order must not be null");

		try {
			return repository.insert(order);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateOrder(final Order order) {
		logger.trace("updateOrder service is started...");
		Assert.notNull(order, "Order must not be null");

		repository.update(order);
	}

	public Order getOrderById(final BigInteger id) {
		logger.trace("getOrderById service is started...");
		Assert.notNull(id, "Id must not be null");

		return repository.queryOne(id);
	}

	public Page<Order> getOrdersByShopId(final int pageNumber, final BigInteger shopId) {
		logger.trace("getOrdersByShopId service is started...");
		Assert.notNull(shopId, "Shop id must not be null");

		return repository.queryByShopId(pageNumber, shopId);
	}

	public Page<Order> getOrdersByClientId(final int pageNumber,
			final BigInteger clientId) {
		logger.trace("getOrdersByClientId service is started...");
		Assert.notNull(clientId, "Client id must not be null");

		return repository.queryByClientId(pageNumber, clientId);
	}

	public Page<Order> getOrdersByStatus(final int pageNumber,
			final BigInteger shopId, final OrderStatus status) {
		logger.trace("getOrdersByStatus service is started...");
		Assert.notNull(shopId, "Shop id must not be null");
		Assert.notNull(status, "Order status must not be null");

		return repository.queryByStatus(pageNumber, shopId, status);
	}
}
