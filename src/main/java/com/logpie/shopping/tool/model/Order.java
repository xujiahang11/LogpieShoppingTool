package com.logpie.shopping.tool.model;

import java.sql.Timestamp;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.ForeignKeyColumn;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.annotation.Entity;
import com.logpie.framework.db.basic.Model;
import com.logpie.shopping.tool.repository.OrderRepository;

@Entity(name = OrderRepository.DB_TABLE_ORDER)
public class Order extends Model {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = OrderRepository.DB_KEY_ORDER_ID, type = DataType.LONG)
	private Long id;

	@AutoGenerate(strategy = AutoGenerateType.CurrentTime)
	@Column(name = OrderRepository.DB_KEY_ORDER_DATE, type = DataType.TIMESTAMP)
	private Timestamp date;

	@ForeignKeyColumn(name = OrderRepository.DB_KEY_ORDER_PRODUCT_ID, referencedEntityClass = Product.class)
	private Product product;

	@Column(name = OrderRepository.DB_KEY_ORDER_IS_RETURN, type = DataType.BOOLEAN)
	private Boolean isReturn;

	@Column(name = OrderRepository.DB_KEY_ORDER_IS_STOCK, type = DataType.BOOLEAN)
	private Boolean isStock;

	@Column(name = OrderRepository.DB_KEY_ORDER_PRODUCT_COUNT, type = DataType.INTEGER)
	private Integer productCount;

	@ForeignKeyColumn(name = OrderRepository.DB_KEY_ORDER_CLIENT_ID, referencedEntityClass = Client.class)
	private Client client;

	@Column(name = OrderRepository.DB_KEY_ORDER_BUYER_NAME, type = DataType.STRING)
	private String buyerName;

	@ForeignKeyColumn(name = OrderRepository.DB_KEY_ORDER_PROXY_ID, referencedEntityClass = Admin.class)
	private Admin proxy;

	@Column(name = OrderRepository.DB_KEY_ORDER_PRODUCT_WEIGHT, type = DataType.INTEGER)
	private Integer productWeight;

	@Column(name = OrderRepository.DB_KEY_ORDER_COST, type = DataType.FLOAT)
	private Float cost;

	@Column(name = OrderRepository.DB_KEY_ORDER_CURRENCY_RATE, type = DataType.FLOAT)
	private Float currencyRate;

	@Column(name = OrderRepository.DB_KEY_ORDER_SELLING_PRICE, type = DataType.FLOAT)
	private Float sellingPrice;

	@Column(name = OrderRepository.DB_KEY_ORDER_CUSTOMER_PAID_MONEY, type = DataType.FLOAT)
	private Float customerPaidMoney;

	@Column(name = OrderRepository.DB_KEY_ORDER_COMPANY_RECEIVED_MONEY, type = DataType.FLOAT)
	private Float companyReceivedMoney;

	@ForeignKeyColumn(name = OrderRepository.DB_KEY_ORDER_PACKAGE_ID, referencedEntityClass = Package.class)
	private Package orderPackage;

	@Column(name = OrderRepository.DB_KEY_ORDER_SHIPPING_FEE, type = DataType.FLOAT)
	private Float shippingFee;

	@ForeignKeyColumn(name = OrderRepository.DB_KEY_ORDER_TRANSFER_DELIVERY_ID, referencedEntityClass = Delivery.class)
	private Delivery transferDelivery;

	@Column(name = OrderRepository.DB_KEY_ORDER_TRANSFER_FEE, type = DataType.FLOAT)
	private Float transferFee;

	@Column(name = OrderRepository.DB_KEY_ORDER_STATUS, type = DataType.STRING)
	private OrderStatus status;

	@Column(name = OrderRepository.DB_KEY_ORDER_NOTE, type = DataType.STRING)
	private String note;

	@ForeignKeyColumn(name = OrderRepository.DB_KEY_ORDER_SHOP_ID, referencedEntityClass = Shop.class)
	private Shop shop;

	public Order() {

	}

	/**
	 * 
	 * @param id
	 * @param date
	 * @param product
	 * @param isReturn
	 * @param isStock
	 * @param client
	 * @param buyerName
	 * @param proxy
	 * @param productWeight
	 * @param cost
	 * @param currencyRate
	 * @param sellingPrice
	 * @param customerPaidMoney
	 * @param companyReceivedMoney
	 * @param orderPackage
	 * @param shippingFee
	 * @param transferDelivery
	 * @param transferFee
	 * @param status
	 * @param note
	 * @param shop
	 */
	public Order(final Long id, final Timestamp date, final Product product,
			final Boolean isReturn, final Boolean isStock, final Client client,
			final String buyerName, final Admin proxy,
			final Integer productWeight, final Float cost,
			final Float currencyRate, final Float sellingPrice,
			final Float customerPaidMoney, final Float companyReceivedMoney,
			final Package orderPackage, final Float shippingFee,
			final Delivery transferDelivery, final Float transferFee,
			final OrderStatus status, final String note, final Shop shop) {
		this.id = id;
		this.date = date;
		this.product = product;
		this.isReturn = isReturn;
		this.isStock = isStock;
		this.client = client;
		this.buyerName = buyerName;
		this.proxy = proxy;
		this.productWeight = productWeight;
		this.cost = cost;
		this.currencyRate = currencyRate;
		this.sellingPrice = sellingPrice;
		this.customerPaidMoney = customerPaidMoney;
		this.companyReceivedMoney = companyReceivedMoney;
		this.orderPackage = orderPackage;
		this.shippingFee = shippingFee;
		this.transferDelivery = transferDelivery;
		this.transferFee = transferFee;
		this.status = status;
		this.note = note;
		this.shop = shop;
	}

	public Long getId() {
		return id;
	}

	public Timestamp getDate() {
		return date;
	}

	public Product getProduct() {
		return product;
	}

	public Boolean getIsReturn() {
		return isReturn;
	}

	public Boolean getIsStock() {
		return isStock;
	}

	public Integer getProductCount() {
		return productCount;
	}

	public Client getClient() {
		return client;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public Admin getProxy() {
		return proxy;
	}

	public Integer getProductWeight() {
		return productWeight;
	}

	public Float getCost() {
		return cost;
	}

	public Float getCurrencyRate() {
		return currencyRate;
	}

	public Float getSellingPrice() {
		return sellingPrice;
	}

	public Float getCustomerPaidMoney() {
		return customerPaidMoney;
	}

	public Float getCompanyReceivedMoney() {
		return companyReceivedMoney;
	}

	public Package getOrderPackage() {
		return orderPackage;
	}

	public Float getShippingFee() {
		return shippingFee;
	}

	public Delivery getTransferDelivery() {
		return transferDelivery;
	}

	public Float getTransferFee() {
		return transferFee;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public String getNote() {
		return note;
	}

	public Shop getShop() {
		return shop;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setIsReturn(Boolean isReturn) {
		this.isReturn = isReturn;
	}

	public void setIsStock(Boolean isStock) {
		this.isStock = isStock;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public void setProxy(Admin proxy) {
		this.proxy = proxy;
	}

	public void setProductWeight(Integer productWeight) {
		this.productWeight = productWeight;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public void setCurrencyRate(Float currencyRate) {
		this.currencyRate = currencyRate;
	}

	public void setSellingPrice(Float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public void setCustomerPaidMoney(Float customerPaidMoney) {
		this.customerPaidMoney = customerPaidMoney;
	}

	public void setCompanyReceivedMoney(Float companyReceivedMoney) {
		this.companyReceivedMoney = companyReceivedMoney;
	}

	public void setOrderPackage(Package orderPackage) {
		this.orderPackage = orderPackage;
	}

	public void setShippingFee(Float shippingFee) {
		this.shippingFee = shippingFee;
	}

	public void setTransferDelivery(Delivery transferDelivery) {
		this.transferDelivery = transferDelivery;
	}

	public void setTransferFee(Float transferFee) {
		this.transferFee = transferFee;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
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
