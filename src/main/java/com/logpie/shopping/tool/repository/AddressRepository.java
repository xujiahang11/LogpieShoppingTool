package com.logpie.shopping.tool.repository;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.logpie.dba.api.basic.Parameter;
import com.logpie.dba.api.basic.WhereParam;
import com.logpie.dba.api.repository.JDBCTemplateRepository;
import com.logpie.shopping.tool.model.Address;

@Repository
public class AddressRepository extends JDBCTemplateRepository<Address> {

	public static final Integer PAGE_SIZE = 10;

	public static final String DB_TABLE_ADDRESS = "Address";

	public static final String DB_KEY_ADDRESS_ID = "addressId";
	public static final String DB_KEY_ADDRESS_ADDR = "address";
	public static final String DB_KEY_ADDRESS_RECIPIENT = "addressRecipient";
	public static final String DB_KEY_ADDRESS_PHONE = "addressPhone";
	public static final String DB_KEY_ADDRESS_ZIP = "addressZip";
	public static final String DB_KEY_ADDRESS_CLIENT_ID = "addressClientId";

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private ShopRepository shopRepository;

	public AddressRepository() {
		super(Address.class);
	}

	public List<Address> queryByClientId(final BigInteger clientId)
			throws DataAccessException {
		Parameter param = new WhereParam(Address.class,
				DB_KEY_ADDRESS_CLIENT_ID, clientId);

		return (List<Address>) super.queryBy(param);
	}
}
