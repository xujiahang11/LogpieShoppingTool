package com.logpie.shopping.tool.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Order;
import com.logpie.shopping.tool.model.Order.OrderStatus;
import com.logpie.shopping.tool.repository.OrderRepository;

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
	private DeliveryService deliveryService;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());

	public Long createOrder(final Order order) {
		if (order == null) {
			logger.error("cannot find order");
			return null;
		}
		try {
			return repository.insert(order);
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateOrder(final Order order) {
		logger.trace("updateOrder service is started...");
		if (order == null) {
			logger.error("cannot find order");
			return;
		}
		repository.update(order);
	}

	public List<Order> getAllOrders(final boolean isAscendingDate) {
		return repository.queryAll(isAscendingDate);
	}

	public Order getOrderById(final Long id) {
		if (id == null) {
			logger.error("cannot find order id");
			return null;
		}
		return repository.queryById(Order.class, id);
	}

	// TODO
	public List<Order> getOrdersByShopId(final Long shopId,
			final boolean isAscendingDate) {
		if (shopId == null) {
			logger.error("cannot find shop Id");
			return null;
		}
		return repository.queryByShopId(shopId, isAscendingDate);
	}

	public List<Order> getOrdersByClientId(final Long clientId,
			final boolean isAscendingDate) {
		if (clientId == null) {
			logger.error("cannot find client Id");
			return null;
		}
		return repository.queryByClientId(clientId, isAscendingDate);
	}

	// TODO
	public List<Order> getOrdersByStatus(final Long shopId,
			final OrderStatus status, final boolean isAscendingDate) {
		if (shopId == null) {
			logger.error("cannot find shop Id");
			return null;
		}
		if (status == null) {
			logger.error("cannot find order status");
			return null;
		}
		return repository.queryByStatus(shopId, status, isAscendingDate);
	}

	// TODO
	public List<Order> getStockOrders(final Long shopId,
			final boolean isAscendingDate) {
		if (shopId == null) {
			logger.error("cannot find shop Id");
			return null;
		}
		return repository.queryByStock(shopId, isAscendingDate);
	}
}
