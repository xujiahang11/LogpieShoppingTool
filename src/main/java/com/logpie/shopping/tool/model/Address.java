package com.logpie.shopping.tool.model;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.Entity;
import com.logpie.framework.db.annotation.ForeignKeyColumn;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.basic.Model;
import com.logpie.shopping.tool.repository.AddressRepository;

@Entity(name = AddressRepository.DB_TABLE_ADDRESS)
public class Address extends Model {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = AddressRepository.DB_KEY_ADDRESS_ID, type = DataType.LONG)
	private Long id;

	@Column(name = AddressRepository.DB_KEY_ADDRESS_ADDR, type = DataType.STRING)
	private String address;

	@Column(name = AddressRepository.DB_KEY_ADDRESS_RECIPENT, type = DataType.STRING)
	private String recipent;

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
	 * @param recipent
	 * @param phone
	 * @param zip
	 * @param client
	 * @param shop
	 */
	public Address(Long id, String address, String recipent, String phone,
			String zip, Client client) {
		this.id = id;
		this.address = address;
		this.recipent = recipent;
		this.phone = phone;
		this.zip = zip;
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public String getAddress() {
		return address;
	}

	public String getRecipent() {
		return recipent;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setRecipent(String recipent) {
		this.recipent = recipent;
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
