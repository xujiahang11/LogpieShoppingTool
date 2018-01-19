package com.logpie.shopping.tool.model;

import com.logpie.dba.api.annotation.Entity;
import com.logpie.dba.api.annotation.ID;
import com.logpie.dba.api.annotation.ForeignKeyColumn;
import com.logpie.dba.api.annotation.Column;
import com.logpie.dba.api.annotation.Column.DataType;
import com.logpie.dba.api.annotation.AutoGenerate;
import com.logpie.dba.api.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.dba.api.basic.Model;
import com.logpie.shopping.tool.repository.ShippingRecordRepository;

import java.math.BigInteger;

@Entity(name = ShippingRecordRepository.DB_TABLE_SHIPPING_RECORD)
public class ShippingRecord extends Model {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = ShippingRecordRepository.DB_KEY_SHIPPING_RECORD_ID, type = DataType.BIGINT)
	private BigInteger id;

	@ForeignKeyColumn(name = ShippingRecordRepository.DB_KEY_SHIPPING_RECORD_PACKAGE_ID, referencedEntityClass = Package.class)
	private Package pack;

	@ForeignKeyColumn(name = ShippingRecordRepository.DB_KEY_SHIPPING_RECORD_TRANSACTION_ID, referencedEntityClass = Transaction.class)
	private Transaction transaction;

	public ShippingRecord() {
		
	}

	public ShippingRecord(final BigInteger id, final Package pack,
			final Transaction transaction) {
		this.id = id;
		this.pack = pack;
		this.transaction = transaction;
	}

	public BigInteger getId() {
		return id;
	}

	public Package getPack() {
		return pack;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public void setPack(Package pack) {
		this.pack = pack;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
}
