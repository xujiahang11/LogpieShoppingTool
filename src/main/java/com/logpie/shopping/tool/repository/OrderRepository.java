package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.logpie.framework.db.basic.SqlClause;
import com.logpie.framework.db.basic.SqlOperator;
import com.logpie.framework.db.util.SqlUtil;
import com.logpie.shopping.tool.model.Admin;
import com.logpie.shopping.tool.model.Client;
import com.logpie.shopping.tool.model.Delivery;
import com.logpie.shopping.tool.model.Order;
import com.logpie.shopping.tool.model.Order.OrderStatus;
import com.logpie.shopping.tool.model.Package;
import com.logpie.shopping.tool.model.Product;
import com.logpie.shopping.tool.model.Shop;

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
	public static final String DB_KEY_ORDER_SHOP_ID = "OrderShopId";

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
	@Autowired
	private ShopRepository shopRepository;

	public List<Order> queryByShopId(final Long shopId,
			final boolean isAscendingDate) throws DataAccessException {
		return super.queryByForeignKey(Order.class, DB_KEY_ORDER_SHOP_ID,
				shopId, orderByDateSQL(isAscendingDate));
	}

	public List<Order> queryByProxyId(final Long adminId,
			final boolean isAscendingDate) throws DataAccessException {
		return super.queryByForeignKey(Order.class, DB_KEY_ORDER_PROXY_ID,
				adminId, orderByDateSQL(isAscendingDate));
	}

	public List<Order> queryByClientId(final Long clientId,
			final boolean isAscendingDate) throws DataAccessException {
		return super.queryByForeignKey(Order.class, DB_KEY_ORDER_CLIENT_ID,
				clientId, orderByDateSQL(isAscendingDate));
	}

	public List<Order> queryByPackageId(final Long packageId,
			final boolean isAscendingDate) throws DataAccessException {
		return super.queryByForeignKey(Order.class, DB_KEY_ORDER_PACKAGE_ID,
				packageId, orderByDateSQL(isAscendingDate));
	}

	public List<Order> queryByStatus(final Long shopId,
			final OrderStatus status, final boolean isAscendingDate)
			throws DataAccessException {
		List<SqlClause> orderByArgs = new ArrayList<SqlClause>();
		orderByArgs.add(SqlClause.createOrderByClause(Order.class, null, null,
				DB_KEY_ORDER_DATE, isAscendingDate));
		List<SqlClause> whereArgs = new ArrayList<SqlClause>();
		whereArgs.add(SqlClause.createWhereClause(Order.class, null, null,
				DB_KEY_ORDER_SHOP_ID, shopId, SqlOperator.EQUAL));
		whereArgs.add(SqlClause.createWhereClause(Order.class, null, null,
				DB_KEY_ORDER_STATUS, status.toString(), SqlOperator.EQUAL));

		String sql = SqlUtil.querySQL(Order.class)
				+ SqlUtil.whereSQL(Order.class, whereArgs)
				+ SqlUtil.orderBySQL(orderByArgs);
		return super.query(sql);
	}

	public List<Order> queryByStock(final Long shopId,
			final boolean isAscendingDate) throws DataAccessException {
		List<SqlClause> orderByArgs = new ArrayList<SqlClause>();
		orderByArgs.add(SqlClause.createOrderByClause(Order.class, null, null,
				DB_KEY_ORDER_DATE, isAscendingDate));
		List<SqlClause> whereArgs = new ArrayList<SqlClause>();
		whereArgs.add(SqlClause.createWhereClause(Order.class, null, null,
				DB_KEY_ORDER_SHOP_ID, shopId, SqlOperator.EQUAL));
		whereArgs.add(SqlClause.createWhereClause(Order.class, null, null,
				DB_KEY_ORDER_IS_STOCK, true, SqlOperator.EQUAL));
		String sql = SqlUtil.querySQL(Order.class)
				+ SqlUtil.whereSQL(Order.class, whereArgs)
				+ SqlUtil.orderBySQL(orderByArgs);
		return super.query(sql);
	}

	public List<Order> queryAll(final boolean isAscendingDate)
			throws DataAccessException {
		return super.queryAll(Order.class, orderByDateSQL(isAscendingDate));
	}

	@Override
	public Order mapRow(final ResultSet rs, final int rowNum)
			throws SQLException {
		Long id = rs.getLong(DB_KEY_ORDER_ID);
		Timestamp date = rs.getTimestamp(DB_KEY_ORDER_DATE);
		Product product = productRepository.mapRow(rs, rowNum);
		Boolean isReturn = rs.getBoolean(DB_KEY_ORDER_IS_RETURN);
		Boolean isStock = rs.getBoolean(DB_KEY_ORDER_IS_STOCK);
		Client client = clientRepository.mapRow(rs, rowNum);
		String buyerName = rs.getString(DB_KEY_ORDER_BUYER_NAME);
		Admin proxy = adminRepository.mapRow(rs, rowNum);
		Integer productWeight = rs.getInt(DB_KEY_ORDER_PRODUCT_WEIGHT);
		Float cost = rs.getFloat(DB_KEY_ORDER_COST);
		Float currencyRate = rs.getFloat(DB_KEY_ORDER_CURRENCY_RATE);
		Float sellingPrice = rs.getFloat(DB_KEY_ORDER_SELLING_PRICE);
		Float customerPaidMoney = rs.getFloat(DB_KEY_ORDER_CUSTOMER_PAID_MONEY);
		Float companyReceivedMoney = rs
				.getFloat(DB_KEY_ORDER_COMPANY_RECEIVED_MONEY);
		Package orderPackage = packageRepository.mapRow(rs, rowNum);
		Float shippingFee = rs.getFloat(DB_KEY_ORDER_SHIPPING_FEE);
		Delivery transferDelivery = deliveryRepository.mapRow(rs, rowNum);
		Float transferFee = rs.getFloat(DB_KEY_ORDER_TRANSFER_FEE);
		OrderStatus status = OrderStatus.fromCode(rs
				.getString(DB_KEY_ORDER_STATUS));
		String note = rs.getString(DB_KEY_ORDER_NOTE);
		Shop shop = shopRepository.mapRow(rs, rowNum);

		return new Order(id, date, product, isReturn, isStock, client,
				buyerName, proxy, productWeight, cost, currencyRate,
				sellingPrice, customerPaidMoney, companyReceivedMoney,
				orderPackage, shippingFee, transferDelivery, transferFee,
				status, note, shop);
	}

	private String orderByDateSQL(final boolean isAscendingDate) {
		List<SqlClause> orderByArgs = new ArrayList<SqlClause>();
		orderByArgs.add(SqlClause.createOrderByClause(Order.class, null, null,
				DB_KEY_ORDER_DATE, isAscendingDate));
		return SqlUtil.orderBySQL(orderByArgs);
	}
}
