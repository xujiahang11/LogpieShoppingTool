package com.logpie.shopping.tool.model;

import com.logpie.dba.api.annotation.Entity;
import com.logpie.dba.api.annotation.ID;
import com.logpie.dba.api.annotation.ForeignKeyColumn;
import com.logpie.dba.api.annotation.Column;
import com.logpie.dba.api.annotation.Column.DataType;
import com.logpie.dba.api.annotation.AutoGenerate;
import com.logpie.dba.api.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.dba.api.basic.Model;
import com.logpie.shopping.tool.repository.AddressRepository;

import java.math.BigInteger;

@Entity(name = AddressRepository.DB_TABLE_ADDRESS)
public class Address extends Model {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = AddressRepository.DB_KEY_ADDRESS_ID, type = DataType.BIGINT)
	private BigInteger id;

	@Column(name = AddressRepository.DB_KEY_ADDRESS_ADDR, type = DataType.STRING)
	private String address;

	@Column(name = AddressRepository.DB_KEY_ADDRESS_RECIPIENT, type = DataType.STRING)
	private String recipient;

	@Column(name = AddressRepository.DB_KEY_ADDRESS_PHONE, type = DataType.STRING)
	private String phone;

	@Column(name = AddressRepository.DB_KEY_ADDRESS_ZIP, type = DataType.STRING)
	private String zip;

	@ForeignKeyColumn(name = AddressRepository.DB_KEY_ADDRESS_CLIENT_ID, referencedEntityClass = Client.class)
	private Client client;

	public Address() {

	}

	/**
	 * 
	 * @param id
	 * @param address
	 * @param recipient
	 * @param phone
	 * @param zip
	 * @param client
	 */
	public Address(BigInteger id, String address, String recipient, String phone,
			String zip, Client client) {
		this.id = id;
		this.address = address;
		this.recipient = recipient;
		this.phone = phone;
		this.zip = zip;
		this.client = client;
	}

	public BigInteger getId() {
		return id;
	}

	public String getAddress() {
		return address;
	}

	public String getRecipient() {
		return recipient;
	}

	public String getPhone() {
		return phone;
	}

	public String getZip() {
		return zip;
	}

	public Client getClient() {
		return client;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
