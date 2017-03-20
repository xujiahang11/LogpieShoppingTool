package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.logpie.framework.db.util.SQLUtil;
import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Address;
import com.logpie.shopping.tool.model.Client;

@Repository
public class AddressRepository extends LogpieRepository<Address> {

	@Autowired
	private ClientRepository repository;

	public static final String DB_TABLE_ADDRESS = "Address";
	public static final String DB_KEY_ADDRESS_ID = "AddressId";
	public static final String DB_KEY_ADDRESS = "Address";
	public static final String DB_KEY_ADDRESS_RECIPENT_NAME = "AddressRecipientName";
	public static final String DB_KEY_ADDRESS_RECIPENT_PHONE = "AddressRecipientPhone";
	public static final String DB_KEY_ADDRESS_ZIP = "AddressZip";
	public static final String DB_KEY_ADDRESS_CLIENT_ID = "AddressClientId";

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());

	@Override
	public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
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

	public List<Address> queryByClientId(Long arg) {
		List<String> param = new ArrayList<String>();
		param.add(DB_KEY_ADDRESS_CLIENT_ID);

		String sql = SQLUtil.querySQL(Address.class)
				+ SQLUtil.whereConditionSQL(Address.class, param);
		logger.debug("'QueryByClientId' SQL: " + sql);

		return jdbcTemplate.query(sql, new Long[] { arg }, this);
	}
}
