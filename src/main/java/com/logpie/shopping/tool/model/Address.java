package com.logpie.shopping.tool.model;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.ForeignKeyColumn;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.annotation.Entity;
import com.logpie.framework.db.basic.Model;
import com.logpie.shopping.tool.repository.AddressRepository;

@Entity(name = AddressRepository.DB_TABLE_ADDRESS)
public class Address extends Model {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = AddressRepository.DB_KEY_ADDRESS_ID, type = DataType.LONG)
	private Long id;

	@Column(name = AddressRepository.DB_KEY_ADDRESS, type = DataType.STRING)
	private String address;

	@Column(name = AddressRepository.DB_KEY_ADDRESS_RECIPENT_NAME, type = DataType.STRING)
	private String recipentName;

	@Column(name = AddressRepository.DB_KEY_ADDRESS_RECIPENT_PHONE, type = DataType.STRING)
	private String recipentPhone;

	@Column(name = AddressRepository.DB_KEY_ADDRESS_ZIP, type = DataType.STRING)
	private String zip;

	@ForeignKeyColumn(name = AddressRepository.DB_KEY_ADDRESS_CLIENT_ID, referencedEntityClass = Client.class)
	private Client client;

	@ForeignKeyColumn(name = AddressRepository.DB_KEY_ADDRESS_SHOP_ID, referencedEntityClass = Shop.class)
	private Shop shop;

	public Address() {

	}

	/**
	 * 
	 * @param id
	 * @param address
	 * @param recipentName
	 * @param recipentPhone
	 * @param zip
	 * @param client
	 * @param shop
	 */
	public Address(Long id, String address, String recipentName,
			String recipentPhone, String zip, Client client, Shop shop) {
		this.id = id;
		this.address = address;
		this.recipentName = recipentName;
		this.recipentPhone = recipentPhone;
		this.zip = zip;
		this.client = client;
		this.shop = shop;
	}

	public Long getId() {
		return id;
	}

	public String getAddress() {
		return address;
	}

	public String getRecipentName() {
		return recipentName;
	}

	public String getRecipentPhone() {
		return recipentPhone;
	}

	public String getZip() {
		return zip;
	}

	public Client getClient() {
		return client;
	}

	public Shop getShop() {
		return shop;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setRecipentName(String recipentName) {
		this.recipentName = recipentName;
	}

	public void setRecipentPhone(String recipentPhone) {
		this.recipentPhone = recipentPhone;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
}
