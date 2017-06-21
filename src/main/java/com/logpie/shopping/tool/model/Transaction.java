package com.logpie.shopping.tool.model;

import java.util.List;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.Entity;
import com.logpie.framework.db.annotation.ForeignKeyColumn;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.basic.Model;
import com.logpie.shopping.tool.repository.TransactionRepository;

@Entity(name = TransactionRepository.DB_TABLE_TRANSACTION)
public class Transaction extends Model {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = TransactionRepository.DB_KEY_TRANSACTION_ID, type = DataType.LONG)
	private Long id;

	@Column(name = TransactionRepository.DB_KEY_TRANSACTION_IS_RETURNED, type = DataType.BOOLEAN)
	private Boolean isReturned;

	@ForeignKeyColumn(name = TransactionRepository.DB_KEY_TRANSACTION_PRODUCT_ID, referencedEntityClass = Product.class)
	private Product product;

	@Column(name = TransactionRepository.DB_KEY_TRANSACTION_QUANTITY, type = DataType.INTEGER)
	private Integer quantity;

	@ForeignKeyColumn(name = TransactionRepository.DB_KEY_TRANSACTION_ORDER_ID, referencedEntityClass = Order.class)
	private Order order;

	@Column(name = TransactionRepository.DB_KEY_TRANSACTION_UNIT_PRICE, type = DataType.FLOAT)
	private Float unitPrice;

	@Column(name = TransactionRepository.DB_KEY_TRANSACTION_PAYMENT, type = DataType.FLOAT)
	private Float payment;

	private List<ShippingRecord> records;

	public Transaction() {

	}

	public Transaction(final Long id, final Boolean isReturned,
			final Product product, final Integer quantity, final Order order,
			final Float unitPrice, final Float payment,
			final List<ShippingRecord> records) {
		this.id = id;
		this.isReturned = isReturned;
		this.product = product;
		this.quantity = quantity;
		this.order = order;
		this.unitPrice = unitPrice;
		this.payment = payment;
		this.records = records;
	}

	public Long getId() {
		return id;
	}

	public Boolean getIsReturned() {
		return isReturned;
	}

	public Product getProduct() {
		return product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Order getOrder() {
		return order;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public Float getPayment() {
		return payment;
	}

	public List<ShippingRecord> getRecords() {
		return records;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIsReturned(Boolean isReturned) {
		this.isReturned = isReturned;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void setPayment(Float payment) {
		this.payment = payment;
	}

	public void setRecords(List<ShippingRecord> records) {
		this.records = records;
	}
}
