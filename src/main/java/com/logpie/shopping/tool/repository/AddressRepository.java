package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.logpie.framework.db.basic.Parameter;
import com.logpie.framework.db.basic.WhereParam;
import com.logpie.framework.db.repository.JDBCTemplateRepository;
import com.logpie.shopping.tool.model.Address;

@Repository
public class AddressRepository extends JDBCTemplateRepository<Address> {

	public static final Integer PAGE_SIZE = 10;

	public static final String DB_TABLE_ADDRESS = "Address";

	public static final String DB_KEY_ADDRESS_ID = "addressId";
	public static final String DB_KEY_ADDRESS_ADDR = "address";
	public static final String DB_KEY_ADDRESS_RECIPENT = "addressRecipient";
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

	public List<Address> queryByClientId(final Long clientId)
			throws DataAccessException {
		Parameter param = new WhereParam(Address.class,
				DB_KEY_ADDRESS_CLIENT_ID, clientId);

		return (List<Address>) super.queryBy(param);
	}

	@Override
	public Address mapRow(final ResultSet rs, final int rowNum)
			throws SQLException {
		Address addr = new Address();
		addr.setId(rs.getLong(DB_KEY_ADDRESS_ID));
		addr.setAddress(rs.getString(DB_KEY_ADDRESS_ADDR));
		addr.setRecipent(rs.getString(DB_KEY_ADDRESS_RECIPENT));
		addr.setPhone(rs.getString(DB_KEY_ADDRESS_PHONE));
		addr.setZip(rs.getString(DB_KEY_ADDRESS_ZIP));
		addr.setClient(clientRepository.mapRow(rs, rowNum));
		return addr;
	}
}
