package com.logpie.shopping.tool.model;

import com.logpie.dba.api.annotation.Entity;
import com.logpie.dba.api.annotation.ID;
import com.logpie.dba.api.annotation.ForeignKeyColumn;
import com.logpie.dba.api.annotation.Column;
import com.logpie.dba.api.annotation.Column.DataType;
import com.logpie.dba.api.annotation.AutoGenerate;
import com.logpie.dba.api.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.dba.api.basic.Model;
import com.logpie.shopping.tool.repository.TransactionRepository;

import java.math.BigInteger;

@Entity(name = TransactionRepository.DB_TABLE_TRANSACTION)
public class Transaction extends Model {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = TransactionRepository.DB_KEY_TRANSACTION_ID, type = DataType.BIGINT)
	private BigInteger id;

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

	public Transaction() {

	}

	public Transaction(final BigInteger id, final Boolean isReturned,
			final Product product, final Integer quantity, final Order order,
			final Float unitPrice, final Float payment) {
		this.id = id;
		this.isReturned = isReturned;
		this.product = product;
		this.quantity = quantity;
		this.order = order;
		this.unitPrice = unitPrice;
		this.payment = payment;
	}

	public BigInteger getId() {
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

	public void setId(BigInteger id) {
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
}
