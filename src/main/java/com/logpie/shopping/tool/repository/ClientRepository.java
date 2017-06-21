package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

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

@Repository
public class ClientRepository extends JDBCTemplateRepository<Client> {

	public static final Integer PAGE_SIZE = 20;

	public static final String DB_TABLE_CLIENT = "Client";

	public static final String DB_KEY_CLIENT_ID = "id";
	public static final String DB_KEY_CLIENT_WECHAT_DIRECTED_ID = "wechatDirectedId";
	public static final String DB_KEY_CLIENT_NAME = "name";
	public static final String DB_KEY_CLIENT_PHONE = "phone";
	public static final String DB_KEY_CLIENT_WECHAT_ID = "wechatId";
	public static final String DB_KEY_CLIENT_WECHAT_NAME = "wechatName";
	public static final String DB_KEY_CLIENT_NOTE = "note";
	public static final String DB_KEY_CLIENT_REGISTER_TIME = "registerTime";
	public static final String DB_KEY_CLIENT_SHOP_ID = "shopId";

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
		Client client = new Client();
		client.setId(rs.getLong(DB_KEY_CLIENT_ID));
		client.setName(rs.getString(DB_KEY_CLIENT_NAME));
		client.setPhone(rs.getString(DB_KEY_CLIENT_PHONE));
		client.setWechatDirectedId(rs
				.getString(DB_KEY_CLIENT_WECHAT_DIRECTED_ID));
		client.setWechatId(rs.getString(DB_KEY_CLIENT_WECHAT_ID));
		client.setWechatName(rs.getString(DB_KEY_CLIENT_WECHAT_NAME));
		client.setNote(rs.getString(DB_KEY_CLIENT_NOTE));
		client.setRegisterTime(rs.getTimestamp(DB_KEY_CLIENT_REGISTER_TIME));
		client.setShop(shopRepository.mapRow(rs, rowNum));

		return client;
	}
}
