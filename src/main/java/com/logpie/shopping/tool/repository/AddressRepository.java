package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.logpie.framework.db.basic.SQLClause;
import com.logpie.framework.db.util.SQLUtil;
import com.logpie.shopping.tool.model.Address;
import com.logpie.shopping.tool.model.Client;

@Repository
public class AddressRepository extends LogpieRepository<Address> {

	public static final String DB_TABLE_ADDRESS = "Address";

	public static final String DB_KEY_ADDRESS_ID = "AddressId";
	public static final String DB_KEY_ADDRESS = "Address";
	public static final String DB_KEY_ADDRESS_RECIPENT_NAME = "AddressRecipientName";
	public static final String DB_KEY_ADDRESS_RECIPENT_PHONE = "AddressRecipientPhone";
	public static final String DB_KEY_ADDRESS_ZIP = "AddressZip";
	public static final String DB_KEY_ADDRESS_CLIENT_ID = "AddressClientId";

	@Autowired
	private ClientRepository repository;

	public List<Address> queryByClientId(final Long arg) {
		return super.queryByForeignKey(Address.class, DB_KEY_ADDRESS_CLIENT_ID,
				arg, null);
	}

	public List<Address> queryAllOrderByClientId(final Boolean isASC) {
		List<SQLClause> args = new ArrayList<SQLClause>();
		args.add(SQLClause.createOrderByClause(DB_KEY_ADDRESS_CLIENT_ID, isASC));
		return super.queryAll(Address.class, SQLUtil.orderBySQL(args));
	}

	@Override
	public Address mapRow(final ResultSet rs, final int rowNum)
			throws SQLException {
		if (rs == null) {
			return null;
		}
		Long addressId = rs.getLong(DB_KEY_ADDRESS_ID);
		String address = rs.getString(DB_KEY_ADDRESS);
		String recipentName = rs.getString(DB_KEY_ADDRESS_RECIPENT_NAME);
		String recipentPhone = rs.getString(DB_KEY_ADDRESS_RECIPENT_PHONE);
		String addressZip = rs.getString(DB_KEY_ADDRESS_ZIP);
		Client client = repository.mapRow(rs, rowNum);
		return new Address(addressId, address, recipentName, recipentPhone,
				addressZip, client);
	}
}
