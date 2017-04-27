package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.logpie.framework.db.basic.Page;
import com.logpie.framework.db.basic.PageRequest;
import com.logpie.framework.db.basic.Pageable;
import com.logpie.framework.db.basic.Parameter;
import com.logpie.framework.db.basic.Sort;
import com.logpie.framework.db.basic.WhereParam;
import com.logpie.framework.db.repository.JDBCTemplateRepository;
import com.logpie.shopping.tool.model.Admin;
import com.logpie.shopping.tool.model.Client;
import com.logpie.shopping.tool.model.Delivery;
import com.logpie.shopping.tool.model.Order;
import com.logpie.shopping.tool.model.Order.OrderStatus;
import com.logpie.shopping.tool.model.Package;
import com.logpie.shopping.tool.model.Product;
import com.logpie.shopping.tool.model.Shop;

@Repository
public class OrderRepository extends JDBCTemplateRepository<Order> {

	public static final Integer PAGE_SIZE = 20;

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

	private Sort sort;

	public OrderRepository() {
		super(Order.class);
		sort = new Sort(Sort.Direction.DESC, DB_KEY_ORDER_DATE);
	}

	public Page<Order> queryByShopId(final int pageNumber, final Long shopId)
			throws DataAccessException {
		Parameter param = new WhereParam(Order.class, DB_KEY_ORDER_SHOP_ID,
				shopId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE, sort);

		return super.queryBy(request, param);
	}

	public Page<Order> queryByProxyId(final int pageNumber, final Long adminId)
			throws DataAccessException {
		Parameter param = new WhereParam(Order.class, DB_KEY_ORDER_PROXY_ID,
				adminId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE, sort);

		return super.queryBy(request, param);
	}

	public Page<Order> queryByClientId(final int pageNumber, final Long clientId)
			throws DataAccessException {
		Parameter param = new WhereParam(Order.class, DB_KEY_ORDER_CLIENT_ID,
				clientId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE, sort);

		return super.queryBy(request, param);
	}

	public Page<Order> queryByPackageId(final int pageNumber,
			final Long packageId) throws DataAccessException {
		Parameter param = new WhereParam(Order.class, DB_KEY_ORDER_PACKAGE_ID,
				packageId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE, sort);

		return super.queryBy(request, param);
	}

	public Page<Order> queryByStatus(final int pageNumber, final Long shopId,
			final OrderStatus status) throws DataAccessException {
		Parameter param_shop = new WhereParam(Order.class,
				DB_KEY_ORDER_SHOP_ID, shopId);
		Parameter param_status = new WhereParam(Order.class,
				DB_KEY_ORDER_STATUS, status.toString());
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE, sort);

		return super.queryBy(request, param_shop, param_status);
	}

	public Page<Order> queryByStock(final int pageNumber, final Long shopId)
			throws DataAccessException {
		Parameter param_shop = new WhereParam(Order.class,
				DB_KEY_ORDER_SHOP_ID, shopId);
		Parameter param_stock = new WhereParam(Order.class,
				DB_KEY_ORDER_IS_STOCK, true);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE, sort);

		return super.queryBy(request, param_shop, param_stock);
	}

	@Override
	public Order mapRow(final ResultSet rs, final int rowNum)
			throws SQLException {
		Long id = rs.getLong(DB_KEY_ORDER_ID);
		if (id == 0) {
			return null;
		}
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
}
