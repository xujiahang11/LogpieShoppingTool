package com.logpie.shopping.tool.model;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.Entity;
import com.logpie.framework.db.annotation.ForeignKeyColumn;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.basic.Model;
import com.logpie.shopping.tool.repository.ShippingRecordRepository;

@Entity(name = ShippingRecordRepository.DB_TABLE_SHIPPING_RECORD)
public class ShippingRecord extends Model {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = ShippingRecordRepository.DB_KEY_SHIPPING_RECORD_ID, type = DataType.LONG)
	private Long id;

	@ForeignKeyColumn(name = ShippingRecordRepository.DB_KEY_SHIPPING_RECORD_PACKAGE_ID, referencedEntityClass = Package.class)
	private Package pack;

	@ForeignKeyColumn(name = ShippingRecordRepository.DB_KEY_SHIPPING_RECORD_TRANSACTION_ID, referencedEntityClass = Transaction.class)
	private Transaction transaction;

	public ShippingRecord() {

	}

	public ShippingRecord(final Long id, final Package pack,
			final Transaction transaction) {
		this.id = id;
		this.pack = pack;
		this.transaction = transaction;
	}

	public Long getId() {
		return id;
	}

	public Package getPack() {
		return pack;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPack(Package pack) {
		this.pack = pack;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
}
