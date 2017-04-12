package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.logpie.shopping.tool.model.Address;
import com.logpie.shopping.tool.model.Client;
import com.logpie.shopping.tool.model.Shop;

@Repository
public class AddressRepository extends LogpieRepository<Address> {

	public static final String DB_TABLE_ADDRESS = "Address";

	public static final String DB_KEY_ADDRESS_ID = "AddressId";
	public static final String DB_KEY_ADDRESS = "Address";
	public static final String DB_KEY_ADDRESS_RECIPENT_NAME = "AddressRecipientName";
	public static final String DB_KEY_ADDRESS_RECIPENT_PHONE = "AddressRecipientPhone";
	public static final String DB_KEY_ADDRESS_ZIP = "AddressZip";
	public static final String DB_KEY_ADDRESS_CLIENT_ID = "AddressClientId";
	public static final String DB_KEY_ADDRESS_SHOP_ID = "AddressShopId";

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private ShopRepository shopRepository;

	public List<Address> queryByShopId(final Long shopId)
			throws DataAccessException {
		return super.queryByForeignKey(Address.class, DB_KEY_ADDRESS_SHOP_ID,
				shopId, null);
	}

	public List<Address> queryByClientId(final Long clientId)
			throws DataAccessException {
		return super.queryByForeignKey(Address.class, DB_KEY_ADDRESS_CLIENT_ID,
				clientId, null);
	}

	@Override
	public Address mapRow(final ResultSet rs, final int rowNum)
			throws SQLException {
		if (rs == null) {
			return null;
		}
		Long id = rs.getLong(DB_KEY_ADDRESS_ID);
		String address = rs.getString(DB_KEY_ADDRESS);
		String recipentName = rs.getString(DB_KEY_ADDRESS_RECIPENT_NAME);
		String recipentPhone = rs.getString(DB_KEY_ADDRESS_RECIPENT_PHONE);
		String addressZip = rs.getString(DB_KEY_ADDRESS_ZIP);
		Client client = clientRepository.mapRow(rs, rowNum);
		Shop shop = shopRepository.mapRow(rs, rowNum);
		return new Address(id, address, recipentName, recipentPhone,
				addressZip, client, shop);
	}
}
