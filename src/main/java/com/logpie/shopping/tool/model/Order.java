package com.logpie.shopping.tool.model;

import java.sql.Timestamp;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.ForeignEntity;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.annotation.Table;
import com.logpie.framework.db.basic.LogpieModel;
import com.logpie.shopping.tool.repository.OrderRepository;

@Table(name = OrderRepository.DB_TABLE_ORDER)
public class Order extends LogpieModel {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = OrderRepository.DB_KEY_ORDER_ID, type = DataType.LONG)
	private Long orderId;
	@AutoGenerate(strategy = AutoGenerateType.CurrentTime)
	@Column(name = OrderRepository.DB_KEY_ORDER_DATE, type = DataType.TIMESTAMP)
	private Timestamp orderDate;
	@ForeignEntity(name = OrderRepository.DB_KEY_ORDER_PRODUCT_ID, referencedTable = Product.class)
	private Product orderProduct;
	@Column(name = OrderRepository.DB_KEY_ORDER_IS_RETURN, type = DataType.BOOLEAN)
	private Boolean orderIsReturn;
	@Column(name = OrderRepository.DB_KEY_ORDER_IS_STOCK, type = DataType.BOOLEAN)
	private Boolean orderIsStock;
	@Column(name = OrderRepository.DB_KEY_ORDER_PRODUCT_COUNT, type = DataType.INTEGER)
	private Integer orderProductCount;
	@ForeignEntity(name = OrderRepository.DB_KEY_ORDER_CLIENT_ID, referencedTable = Client.class, referencedTableAlias = "orderClient")
	private Client orderClient;
	@Column(name = OrderRepository.DB_KEY_ORDER_BUYER_NAME, type = DataType.STRING)
	private String orderBuyerName;
	@ForeignEntity(name = OrderRepository.DB_KEY_ORDER_PROXY_ID, referencedTable = Admin.class)
	private Admin orderProxy;
	@Column(name = OrderRepository.DB_KEY_ORDER_PRODUCT_WEIGHT, type = DataType.INTEGER)
	private Integer orderProductWeight;
	@Column(name = OrderRepository.DB_KEY_ORDER_COST, type = DataType.FLOAT)
	private Float orderCost;
	@Column(name = OrderRepository.DB_KEY_ORDER_CURRENCY_RATE, type = DataType.FLOAT)
	private Float orderCurrencyRate;
	@Column(name = OrderRepository.DB_KEY_ORDER_SELLING_PRICE, type = DataType.FLOAT)
	private Float orderSellingPrice;
	@Column(name = OrderRepository.DB_KEY_ORDER_CUSTOMER_PAID_MONEY, type = DataType.FLOAT)
	private Float orderCustomerPaidMoney;
	@Column(name = OrderRepository.DB_KEY_ORDER_COMPANY_RECEIVED_MONEY, type = DataType.FLOAT)
	private Float orderCompanyReceivedMoney;
	@ForeignEntity(name = OrderRepository.DB_KEY_ORDER_PACKAGE_ID, referencedTable = Package.class)
	private Package orderPackage;
	@Column(name = OrderRepository.DB_KEY_ORDER_SHIPPING_FEE, type = DataType.FLOAT)
	private Float orderShippingFee;
	@ForeignEntity(name = OrderRepository.DB_KEY_ORDER_TRANSFER_DELIVERY_ID, referencedTable = Delivery.class)
	private Delivery orderTransferDelivery;
	@Column(name = OrderRepository.DB_KEY_ORDER_TRANSFER_FEE, type = DataType.FLOAT)
	private Float orderTransferFee;
	@Column(name = OrderRepository.DB_KEY_ORDER_STATUS, type = DataType.STRING)
	private OrderStatus orderStatus;
	@Column(name = OrderRepository.DB_KEY_ORDER_NOTE, type = DataType.STRING)
	private String orderNote;

	/**
	 * constructor for creating an order
	 * 
	 * @param orderProduct
	 * @param orderClient
	 * @param orderBuyerName
	 * @param orderProxy
	 * @param orderProductWeight
	 * @param orderCost
	 * @param orderCurrencyRate
	 * @param orderSellingPrice
	 */
	public Order(final Product orderProduct, final Client orderClient,
			final String orderBuyerName, final Admin orderProxy,
			final Integer orderProductWeight, final Float orderCost,
			final Float orderCurrencyRate, final Float orderSellingPrice) {
		this(null, null, orderProduct, false, false, orderClient,
				orderBuyerName, orderProxy, orderProductWeight, orderCost,
				orderCurrencyRate, orderSellingPrice, 0.0F, 0.0F, null, 0.0F,
				null, 0.0F, OrderStatus.TO_BE_SHIPPED, null);
	}

	/**
	 * 
	 * @param orderId
	 * @param orderDate
	 * @param orderProduct
	 * @param orderIsReturn
	 * @param orderIsStock
	 * @param orderClient
	 * @param orderBuyerName
	 * @param orderProxy
	 * @param orderProductWeight
	 * @param orderCost
	 * @param orderCurrencyRate
	 * @param orderSellingPrice
	 * @param orderCustomerPaidMoney
	 * @param orderCompanyReceivedMoney
	 * @param orderPackage
	 * @param orderShippingFee
	 * @param orderTransferDelivery
	 * @param orderTransferFee
	 * @param orderStatus
	 * @param orderNote
	 */
	public Order(final Long orderId, final Timestamp orderDate,
			final Product orderProduct, final Boolean orderIsReturn,
			final Boolean orderIsStock, final Client orderClient,
			final String orderBuyerName, final Admin orderProxy,
			final Integer orderProductWeight, final Float orderCost,
			final Float orderCurrencyRate, final Float orderSellingPrice,
			final Float orderCustomerPaidMoney,
			final Float orderCompanyReceivedMoney, final Package orderPackage,
			final Float orderShippingFee, final Delivery orderTransferDelivery,
			final Float orderTransferFee, final OrderStatus orderStatus,
			final String orderNote) {
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.orderProduct = orderProduct;
		this.orderIsReturn = orderIsReturn;
		this.orderIsStock = orderIsStock;
		this.orderClient = orderClient;
		this.orderBuyerName = orderBuyerName;
		this.orderProxy = orderProxy;
		this.orderProductWeight = orderProductWeight;
		this.orderCost = orderCost;
		this.orderCurrencyRate = orderCurrencyRate;
		this.orderSellingPrice = orderSellingPrice;
		this.orderCustomerPaidMoney = orderCustomerPaidMoney;
		this.orderCompanyReceivedMoney = orderCompanyReceivedMoney;
		this.orderPackage = orderPackage;
		this.orderShippingFee = orderShippingFee;
		this.orderTransferDelivery = orderTransferDelivery;
		this.orderTransferFee = orderTransferFee;
		this.orderStatus = orderStatus;
		this.orderNote = orderNote;
	}

	public Long getOrderId() {
		return orderId;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public Product getOrderProduct() {
		return orderProduct;
	}

	public Boolean getOrderIsReturn() {
		return orderIsReturn;
	}

	public Boolean getOrderIsStock() {
		return orderIsStock;
	}

	public Integer getOrderProductCount() {
		return orderProductCount;
	}

	public Client getOrderClient() {
		return orderClient;
	}

	public String getOrderBuyerName() {
		return orderBuyerName;
	}

	public Admin getOrderProxy() {
		return orderProxy;
	}

	public Integer getOrderProductWeight() {
		return orderProductWeight;
	}

	public Float getOrderCost() {
		return orderCost;
	}

	public Float getOrderCurrencyRate() {
		return orderCurrencyRate;
	}

	public Float getOrderSellingPrice() {
		return orderSellingPrice;
	}

	public Float getOrderCustomerPaidMoney() {
		return orderCustomerPaidMoney;
	}

	public Float getOrderCompanyReceivedMoney() {
		return orderCompanyReceivedMoney;
	}

	public Package getOrderPackage() {
		return orderPackage;
	}

	public Float getOrderShippingFee() {
		return orderShippingFee;
	}

	public Delivery getOrderTransferDelivery() {
		return orderTransferDelivery;
	}

	public Float getOrderTransferFee() {
		return orderTransferFee;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public String getOrderNote() {
		return orderNote;
	}

	public void setOrderProduct(Product orderProduct) {
		this.orderProduct = orderProduct;
	}

	public void setOrderIsReturn(Boolean orderIsReturn) {
		this.orderIsReturn = orderIsReturn;
	}

	public void setOrderIsStock(Boolean orderIsStock) {
		this.orderIsStock = orderIsStock;
	}

	public void setOrderProductCount(Integer orderProductCount) {
		this.orderProductCount = orderProductCount;
	}

	public void setOrderClient(Client orderClient) {
		this.orderClient = orderClient;
	}

	public void setOrderBuyerName(String orderBuyerName) {
		this.orderBuyerName = orderBuyerName;
	}

	public void setOrderProxy(Admin orderProxy) {
		this.orderProxy = orderProxy;
	}

	public void setOrderProductWeight(Integer orderProductWeight) {
		this.orderProductWeight = orderProductWeight;
	}

	public void setOrderCost(Float orderCost) {
		this.orderCost = orderCost;
	}

	public void setOrderCurrencyRate(Float orderCurrencyRate) {
		this.orderCurrencyRate = orderCurrencyRate;
	}

	public void setOrderSellingPrice(Float orderSellingPrice) {
		this.orderSellingPrice = orderSellingPrice;
	}

	public void setOrderCustomerPaidMoney(Float orderCustomerPaidMoney) {
		this.orderCustomerPaidMoney = orderCustomerPaidMoney;
	}

	public void setOrderCompanyReceivedMoney(Float orderCompanyReceivedMoney) {
		this.orderCompanyReceivedMoney = orderCompanyReceivedMoney;
	}

	public void setOrderPackage(Package orderPackage) {
		this.orderPackage = orderPackage;
	}

	public void setOrderShippingFee(Float orderShippingFee) {
		this.orderShippingFee = orderShippingFee;
	}

	public void setOrderTransferDelivery(Delivery orderTransferDelivery) {
		this.orderTransferDelivery = orderTransferDelivery;
	}

	public void setOrderTransferFee(Float orderTransferFee) {
		this.orderTransferFee = orderTransferFee;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}

	public enum OrderStatus {
		TO_BE_SHIPPED("1", "待发货"), TO_BE_TRANFERED("2", "待转寄"), TO_BE_DELIVERED(
				"3", "待收货"), TO_BE_CLOSE("4", "待结算");

		private final String code;
		private final String text;

		private OrderStatus(final String code, final String text) {
			this.text = text;
			this.code = code;
		}

		@Override
		public String toString() {
			return code;
		}

		public String getText() {
			return text;
		}

		public static OrderStatus fromCode(String code) {
			for (OrderStatus status : OrderStatus.values()) {
				if (status.toString().equals(code)) {
					return status;
				}
			}
			return null;
		}
	}
}
