package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.logpie.framework.db.basic.Page;
import com.logpie.framework.db.basic.PageRequest;
import com.logpie.framework.db.basic.Pageable;
import com.logpie.framework.db.basic.Parameter;
import com.logpie.framework.db.basic.WhereParam;
import com.logpie.framework.db.repository.JDBCTemplateRepository;
import com.logpie.shopping.tool.model.Client;
import com.logpie.shopping.tool.model.Shop;

@Repository
public class ClientRepository extends JDBCTemplateRepository<Client> {

	public static final Integer PAGE_SIZE = 20;

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
	public static final String DB_KEY_CLIENT_SHOP_ID = "ClientShopId";

	@Autowired
	private ShopRepository shopRepository;

	public ClientRepository() {
		super(Client.class);
	}

	public Page<Client> queryByShopId(final int pageNumber, final Long shopId)
			throws DataAccessException {
		Parameter param = new WhereParam(Client.class, DB_KEY_CLIENT_SHOP_ID,
				shopId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE);

		return super.queryBy(request, param);
	}

	@Override
	public Client mapRow(final ResultSet rs, final int rowNum)
			throws SQLException {
		Long id = rs.getLong(DB_KEY_CLIENT_ID);
		String wechatDirectedId = rs
				.getString(DB_KEY_CLIENT_WECHAT_DIRECTED_ID);
		String name = rs.getString(DB_KEY_CLIENT_NAME);
		String phone = rs.getString(DB_KEY_CLIENT_PHONE);
		String wechatId = rs.getString(DB_KEY_CLIENT_WECHAT_ID);
		String wechatName = rs.getString(DB_KEY_CLIENT_WECHAT_NAME);
		String taobaoName = rs.getString(DB_KEY_CLIENT_TAOBAO_NAME);
		String note = rs.getString(DB_KEY_CLIENT_NOTE);
		Timestamp registerTime = rs.getTimestamp(DB_KEY_CLIENT_REGISTER_TIME);
		Shop shop = shopRepository.mapRow(rs, rowNum);

		return new Client(id, wechatDirectedId, name, phone, wechatId,
				wechatName, taobaoName, note, registerTime, shop);
	}
}
