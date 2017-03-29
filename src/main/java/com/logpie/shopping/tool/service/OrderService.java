package com.logpie.shopping.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Admin;
import com.logpie.shopping.tool.model.Client;
import com.logpie.shopping.tool.model.Delivery;
import com.logpie.shopping.tool.model.Order;
import com.logpie.shopping.tool.model.Order.OrderStatus;
import com.logpie.shopping.tool.model.Package;
import com.logpie.shopping.tool.model.Product;
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

	public Long createOrder(final Long orderProductId,
			final Boolean orderIsReturn, final Boolean orderIsStock,
			final Long orderClientId, final String orderBuyerName,
			final Long orderProxyId, final Integer orderProductWeight,
			final Float orderCost, final Float orderCurrencyRate,
			final Float orderSellingPrice, final Float orderCustomerPaidMoney,
			final Float orderCompanyReceivedMoney, final Long orderPackageId,
			final Float orderShippingFee, final Long orderTransferDeliveryId,
			final Float orderTransferFee, final OrderStatus orderStatus,
			final String orderNote) {
		return createOrder(productService.getProductById(orderProductId),
				orderIsReturn, orderIsStock,
				clientService.getClientById(orderClientId), orderBuyerName,
				adminService.getAdminById(orderProxyId), orderProductWeight,
				orderCost, orderCurrencyRate, orderSellingPrice,
				orderCustomerPaidMoney, orderCompanyReceivedMoney,
				packageService.getPackageById(orderPackageId),
				orderShippingFee,
				deliveryService.getDeliveryById(orderTransferDeliveryId),
				orderTransferFee, orderStatus, orderNote);
	}

	public Long createOrder(final Product orderProduct,
			final Boolean orderIsReturn, final Boolean orderIsStock,
			final Client orderClient, final String orderBuyerName,
			final Admin orderProxy, final Integer orderProductWeight,
			final Float orderCost, final Float orderCurrencyRate,
			final Float orderSellingPrice, final Float orderCustomerPaidMoney,
			final Float orderCompanyReceivedMoney, final Package orderPackage,
			final Float orderShippingFee, final Delivery orderTransferDelivery,
			final Float orderTransferFee, final OrderStatus orderStatus,
			final String orderNote) {
		if (orderProduct == null) {
			logger.error("cannot find product");
			return null;
		}
		if (orderClient == null
				&& (orderBuyerName == null || orderBuyerName.isEmpty())) {
			logger.error("cannot find buyer name");
			return null;
		}
		// pre-handle initial values of some properties
		String name = orderClient == null ? orderBuyerName : orderClient
				.getClientName();
		Integer weight = (orderProductWeight == null || orderProductWeight
				.intValue() == 0) ? orderProduct.getProductWeight()
				: orderProductWeight;
		OrderStatus status = orderStatus == null ? OrderStatus.TO_BE_SHIPPED
				: orderStatus;

		Order order = new Order(null, null, orderProduct, orderIsReturn,
				orderIsStock, orderClient, name, orderProxy, weight, orderCost,
				orderCurrencyRate, orderSellingPrice, orderCustomerPaidMoney,
				orderCompanyReceivedMoney, orderPackage, orderShippingFee,
				orderTransferDelivery, orderTransferFee, status, orderNote);
		return repository.insert(order);
	}

	public List<Order> getAllOrders(final boolean isAscendingDate) {
		return repository.queryAll(isAscendingDate);
	}

	public Order getOrderById(final Long orderId) {
		if (orderId == null) {
			logger.error("cannot find order Id");
			return null;
		}
		return repository.queryByID(Order.class, orderId);
	}

	public List<Order> getOrdersByClientId(final Long clientId) {
		if (clientId == null) {
			logger.error("cannot find category Id");
			return null;
		}
		return repository.queryByClientId(clientId);
	}

	public List<Order> getOrdersByOrderStatus(final OrderStatus arg,
			final boolean isAscendingDate) {
		if (arg == null) {
			logger.error("cannot find order status");
			return null;
		}
		return repository.queryByOrderStatus(arg, isAscendingDate);
	}
}
