package com.logpie.shopping.tool.repository;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.logpie.dba.api.basic.Page;
import com.logpie.dba.api.basic.PageRequest;
import com.logpie.dba.api.basic.Pageable;
import com.logpie.dba.api.basic.Parameter;
import com.logpie.dba.api.basic.WhereParam;
import com.logpie.dba.api.repository.JDBCTemplateRepository;
import com.logpie.shopping.tool.model.Client;

@Repository
public class ClientRepository extends JDBCTemplateRepository<Client> {

	public static final Integer PAGE_SIZE = 20;

	public static final String DB_TABLE_CLIENT = "Client";

	public static final String DB_KEY_CLIENT_ID = "clientId";
	public static final String DB_KEY_CLIENT_WECHAT_DIRECTED_ID = "clientWechatDirectedId";
	public static final String DB_KEY_CLIENT_NAME = "clientName";
	public static final String DB_KEY_CLIENT_PHONE = "clientPhone";
	public static final String DB_KEY_CLIENT_WECHAT_ID = "clientWechatId";
	public static final String DB_KEY_CLIENT_WECHAT_NAME = "clientWechatName";
	public static final String DB_KEY_CLIENT_NOTE = "clientNote";
	public static final String DB_KEY_CLIENT_REGISTER_TIME = "clientRegisterTime";
	public static final String DB_KEY_CLIENT_SHOP_ID = "clientShopId";

	@Autowired
	private ShopRepository shopRepository;

	public ClientRepository() {
		super(Client.class);
	}

	public Page<Client> queryByShopId(final int pageNumber, final BigInteger shopId)
			throws DataAccessException {
		Parameter param = new WhereParam(Client.class, DB_KEY_CLIENT_SHOP_ID,
				shopId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE);

		return super.queryBy(request, param);
	}
}
