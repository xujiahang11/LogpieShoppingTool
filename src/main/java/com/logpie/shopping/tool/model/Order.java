package com.logpie.shopping.tool.model;

import java.sql.Timestamp;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.Entity;
import com.logpie.framework.db.annotation.ForeignKeyColumn;
import com.logpie.framework.db.annotation.ID;
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

	@ForeignKeyColumn(name = OrderRepository.DB_KEY_ORDER_CLIENT_ID, referencedEntityClass = Client.class)
	private Client client;

	@Column(name = OrderRepository.DB_KEY_ORDER_CUSTOMER, type = DataType.STRING)
	private String customer;

	@ForeignKeyColumn(name = OrderRepository.DB_KEY_ORDER_ADMIN_ID, referencedEntityClass = Admin.class)
	private Admin admin;

	@Column(name = OrderRepository.DB_KEY_ORDER_FINAL_PRICE, type = DataType.FLOAT)
	private Float finalPrice;

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
	 * @param client
	 * @param customer
	 * @param admin
	 * @param finalPrice
	 * @param status
	 * @param note
	 * @param shop
	 */
	public Order(final Long id, final Timestamp date, final Client client,
			final String customer, final Admin admin, final Float finalPrice,
			final OrderStatus status, final String note, final Shop shop) {
		this.id = id;
		this.date = date;
		this.client = client;
		this.customer = customer;
		this.admin = admin;
		this.finalPrice = finalPrice;
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

	public Client getClient() {
		return client;
	}

	public String getCustomer() {
		return customer;
	}

	public Admin getAdmin() {
		return admin;
	}

	public Float getFinalPrice() {
		return finalPrice;
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

	public void setClient(Client client) {
		this.client = client;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public void setFinalPrice(Float finalPrice) {
		this.finalPrice = finalPrice;
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
