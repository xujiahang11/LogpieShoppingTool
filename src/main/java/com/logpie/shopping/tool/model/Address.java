package com.logpie.shopping.tool.model;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.ForeignEntity;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.annotation.Table;
import com.logpie.shopping.tool.repository.AddressRepository;
import com.logpie.shopping.tool.repository.ClientRepository;

@Table(name = AddressRepository.DB_TABLE_ADDRESS)
public class Address extends LogpieModel {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = AddressRepository.DB_KEY_ADDRESS_ID, type = DataType.LONG)
	private Long addressId;

	@Column(name = AddressRepository.DB_KEY_ADDRESS, type = DataType.STRING)
	private String address;

	@Column(name = AddressRepository.DB_KEY_ADDRESS_RECIPENT_NAME, type = DataType.STRING)
	private String addressRecipentName;

	@Column(name = AddressRepository.DB_KEY_ADDRESS_RECIPENT_PHONE, type = DataType.STRING)
	private String addressRecipentPhone;

	@Column(name = AddressRepository.DB_KEY_ADDRESS_ZIP, type = DataType.STRING)
	private String addressZip;

	@ForeignEntity(name = AddressRepository.DB_KEY_ADDRESS_CLIENT_ID, referencedColumn = ClientRepository.DB_KEY_CLIENT_ID, referencedTable = Client.class)
	private Client client;

	/**
	 * constructor for creating an address
	 * 
	 * @param address
	 * @param recipentName
	 * @param recipentPhone
	 * @param client
	 */
	public Address(String address, String recipentName, String recipentPhone,
			Client client) {
		this(null, address, recipentName, recipentPhone, null, client);
	}

	public Address(Long addressId, String address, String recipentName,
			String recipentPhone, String addressZip, Client client) {
		this.addressId = addressId;
		this.address = address;
		this.addressRecipentName = recipentName;
		this.addressRecipentPhone = recipentPhone;
		this.addressZip = addressZip;
		this.client = client;
	}

	public Long getAddressId() {
		return addressId;
	}

	public String getAddress() {
		return address;
	}

	public String getAddressRecipentName() {
		return addressRecipentName;
	}

	public String getAddressRecipentPhone() {
		return addressRecipentPhone;
	}

	public String getAddressZip() {
		return addressZip;
	}

	public Client getClient() {
		return client;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAddressRecipentName(String addressRecipentName) {
		this.addressRecipentName = addressRecipentName;
	}

	public void setAddressRecipentPhone(String addressRecipentPhone) {
		this.addressRecipentPhone = addressRecipentPhone;
	}

	public void setAddressZip(String addressZip) {
		this.addressZip = addressZip;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
