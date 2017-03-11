package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.stereotype.Repository;

import com.logpie.shopping.tool.model.Client;

@Repository
public class ClientRepository extends LogpieRepository<Client> {
	public static final String DB_TABLE_CLIENT = "Client";

	public static final String DB_KEY_CLIENT_ID = "ClientId";
	public static final String DB_KEY_CLIENT_WECHAT_DIRECTED_ID = "ClientWechatDirectedId";
	public static final String DB_KEY_CLIENT_NAME = "ClientName";
	public static final String DB_KEY_CLIENT_PHONE = "ClientPhone";
	public static final String DB_KEY_CLIENT_WECHAT_ID = "ClientWechatId";
	public static final String DB_KEY_CLIENT_WECHAT_NAME = "ClientWechatName";
	public static final String DB_KEY_CLIENT_TAOBAO_NAME = "ClientTaobaoName";
	public static final String DB_KEY_CLIENT_NOTE = "ClientNote";
	public static final String DB_KEY_CLIENT_REGISTER_TIME = "ClientRegisterTime";

	@Override
	public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long ClientId = rs.getLong(DB_KEY_CLIENT_ID);
		String ClientWechatDirectedId = rs
				.getString(DB_KEY_CLIENT_WECHAT_DIRECTED_ID);
		String ClientName = rs.getString(DB_KEY_CLIENT_NAME);
		String ClientPhone = rs.getString(DB_KEY_CLIENT_PHONE);
		String ClientWechatId = rs.getString(DB_KEY_CLIENT_WECHAT_ID);
		String ClientWechatName = rs.getString(DB_KEY_CLIENT_WECHAT_NAME);
		String ClientTaobaoName = rs.getString(DB_KEY_CLIENT_TAOBAO_NAME);
		String ClientNote = rs.getString(DB_KEY_CLIENT_NOTE);
		Timestamp ClientRegisterTime = rs
				.getTimestamp(DB_KEY_CLIENT_REGISTER_TIME);

		return new Client(ClientId, ClientWechatDirectedId, ClientName,
				ClientPhone, ClientWechatId, ClientWechatName,
				ClientTaobaoName, ClientNote, ClientRegisterTime);
	}
}
