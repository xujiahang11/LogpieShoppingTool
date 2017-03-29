package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.logpie.framework.db.basic.SQLClause;
import com.logpie.framework.db.util.SQLUtil;
import com.logpie.shopping.tool.model.Admin;
import com.logpie.shopping.tool.model.Client;
import com.logpie.shopping.tool.model.Delivery;
import com.logpie.shopping.tool.model.Order;
import com.logpie.shopping.tool.model.Order.OrderStatus;
import com.logpie.shopping.tool.model.Package;
import com.logpie.shopping.tool.model.Product;

@Repository
public class OrderRepository extends LogpieRepository<Order> {

	public static final String DB_TABLE_ORDER = "Orders";

	public static final String DB_KEY_ORDER_ID = "OrderId";
	public static final String DB_KEY_ORDER_DATE = "OrderDate";
	public static final String DB_KEY_ORDER_PRODUCT_ID = "OrderProductId";
	public static final String DB_KEY_ORDER_IS_RETURN = "OrderIsReturn";
	public static final String DB_KEY_ORDER_IS_STOCK = "OrderIsStock";
	public static final String DB_KEY_ORDER_PRODUCT_COUNT = "OrderProductCount";
	public static final String DB_KEY_ORDER_CLIENT_ID = "OrderClientId";
	public static final String DB_KEY_ORDER_BUYER_NAME = "OrderBuyerName";
	public static final String DB_KEY_ORDER_PROXY_ID = "OrderProxyId";
	public static final String DB_KEY_ORDER_PRODUCT_WEIGHT = "OrderProductWeight";
	public static final String DB_KEY_ORDER_COST = "OrderCost";
	public static final String DB_KEY_ORDER_CURRENCY_RATE = "OrderCurrencyRate";
	public static final String DB_KEY_ORDER_SELLING_PRICE = "OrderSellingPrice";
	public static final String DB_KEY_ORDER_CUSTOMER_PAID_MONEY = "OrderCustomerPaidMoney";
	public static final String DB_KEY_ORDER_COMPANY_RECEIVED_MONEY = "OrderCompanyReceivedMoney";
	public static final String DB_KEY_ORDER_PACKAGE_ID = "OrderPackageId";
	public static final String DB_KEY_ORDER_SHIPPING_FEE = "OrderShippingFee";
	public static final String DB_KEY_ORDER_TRANSFER_DELIVERY_ID = "OrderTransferDeliveryId";
	public static final String DB_KEY_ORDER_TRANSFER_FEE = "OrderTransferFee";
	public static final String DB_KEY_ORDER_STATUS = "OrderStatus";
	public static final String DB_KEY_ORDER_NOTE = "OrderNote";

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private PackageRepository packageRepository;
	@Autowired
	private DeliveryRepository deliveryRepository;

	public List<Order> queryByProxyId(final Long arg) {
		return super.queryByForeignKey(Order.class, DB_KEY_ORDER_PROXY_ID, arg,
				getOrderByDateSQL(false));
	}

	public List<Order> queryByClientId(final Long arg) {
		return super.queryByForeignKey(Order.class, DB_KEY_ORDER_CLIENT_ID,
				arg, getOrderByDateSQL(false));
	}

	public List<Order> queryByPackageId(final Long arg) {
		return super.queryByForeignKey(Order.class, DB_KEY_ORDER_PACKAGE_ID,
				arg, getOrderByDateSQL(false));
	}

	public List<Order> queryByOrderStatus(final OrderStatus arg,
			final boolean isAscendingDate) {
		List<SQLClause> orderByArgs = new ArrayList<SQLClause>();
		orderByArgs.add(SQLClause.createOrderByClause(DB_KEY_ORDER_DATE,
				isAscendingDate));
		List<SQLClause> whereArgs = new ArrayList<SQLClause>();
		whereArgs.add(SQLClause.createWhereClause(DB_KEY_ORDER_STATUS,
				arg.toString()));

		String sql = SQLUtil.querySQL(Order.class)
				+ SQLUtil.orderBySQL(orderByArgs)
				+ SQLUtil.whereSQL(Order.class, whereArgs);
		return super.query(sql);
	}

	public List<Order> queryAll(final boolean isAscendingDate) {
		return super.queryAll(Order.class, getOrderByDateSQL(isAscendingDate));
	}

	@Override
	public Order mapRow(final ResultSet rs, final int rowNum)
			throws SQLException {
		Long orderId = rs.getLong(DB_KEY_ORDER_ID);
		Timestamp orderDate = rs.getTimestamp(DB_KEY_ORDER_DATE);
		Product orderProduct = productRepository.mapRow(rs, rowNum);
		Boolean orderIsReturn = rs.getBoolean(DB_KEY_ORDER_IS_RETURN);
		Boolean orderIsStock = rs.getBoolean(DB_KEY_ORDER_IS_STOCK);
		Client orderClient = clientRepository.mapRow(rs, rowNum);
		String orderBuyerName = rs.getString(DB_KEY_ORDER_BUYER_NAME);
		Admin orderProxy = adminRepository.mapRow(rs, rowNum);
		Integer orderProductWeight = rs.getInt(DB_KEY_ORDER_PRODUCT_WEIGHT);
		Float orderCost = rs.getFloat(DB_KEY_ORDER_COST);
		Float orderCurrencyRate = rs.getFloat(DB_KEY_ORDER_CURRENCY_RATE);
		Float orderSellingPrice = rs.getFloat(DB_KEY_ORDER_SELLING_PRICE);
		Float orderCustomerPaidMoney = rs
				.getFloat(DB_KEY_ORDER_CUSTOMER_PAID_MONEY);
		Float orderCompanyReceivedMoney = rs
				.getFloat(DB_KEY_ORDER_COMPANY_RECEIVED_MONEY);
		Package orderPackage = packageRepository.mapRow(rs, rowNum);
		Float orderShippingFee = rs.getFloat(DB_KEY_ORDER_SHIPPING_FEE);
		Delivery orderTransferDelivery = deliveryRepository.mapRow(rs, rowNum);
		Float orderTransferFee = rs.getFloat(DB_KEY_ORDER_TRANSFER_FEE);
		OrderStatus orderStatus = OrderStatus.fromCode(rs
				.getString(DB_KEY_ORDER_STATUS));
		String orderNote = rs.getString(DB_KEY_ORDER_NOTE);

		return new Order(orderId, orderDate, orderProduct, orderIsReturn,
				orderIsStock, orderClient, orderBuyerName, orderProxy,
				orderProductWeight, orderCost, orderCurrencyRate,
				orderSellingPrice, orderCustomerPaidMoney,
				orderCompanyReceivedMoney, orderPackage, orderShippingFee,
				orderTransferDelivery, orderTransferFee, orderStatus, orderNote);
	}

	private String getOrderByDateSQL(final boolean isAscendingDate) {
		List<SQLClause> orderByArgs = new ArrayList<SQLClause>();
		orderByArgs.add(SQLClause.createOrderByClause(DB_KEY_ORDER_DATE,
				isAscendingDate));
		return SQLUtil.orderBySQL(orderByArgs);
	}
}
