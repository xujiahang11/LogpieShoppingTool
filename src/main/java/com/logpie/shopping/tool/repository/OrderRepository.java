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
import com.logpie.dba.api.basic.Sort;
import com.logpie.dba.api.basic.WhereParam;
import com.logpie.dba.api.repository.JDBCTemplateRepository;
import com.logpie.shopping.tool.model.Order;
import com.logpie.shopping.tool.model.Order.OrderStatus;

@Repository
public class OrderRepository extends JDBCTemplateRepository<Order> {

	public static final Integer PAGE_SIZE = 20;

	public static final String DB_TABLE_ORDER = "Orders";

	public static final String DB_KEY_ORDER_ID = "orderId";
	public static final String DB_KEY_ORDER_DATE = "orderPostDate";
	public static final String DB_KEY_ORDER_CUSTOMER = "orderCustomer";
	public static final String DB_KEY_ORDER_CLIENT_ID = "orderClientId";
	public static final String DB_KEY_ORDER_ADMIN_ID = "orderAdminId";
	public static final String DB_KEY_ORDER_FINAL_PRICE = "orderFinalPrice";
	public static final String DB_KEY_ORDER_STATUS = "orderStatus";
	public static final String DB_KEY_ORDER_NOTE = "orderNote";
	public static final String DB_KEY_ORDER_SHOP_ID = "orderShopId";

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private ShopRepository shopRepository;

	private Sort sort;

	public OrderRepository() {
		super(Order.class);
		sort = new Sort(Sort.Direction.DESC, DB_KEY_ORDER_DATE);
	}

	public Page<Order> queryByShopId(final int pageNumber, final BigInteger shopId)
			throws DataAccessException {
		Parameter param = new WhereParam(Order.class, DB_KEY_ORDER_SHOP_ID,
				shopId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE, sort);

		return super.queryBy(request, param);
	}

	public Page<Order> queryByAdminId(final int pageNumber, final BigInteger adminId)
			throws DataAccessException {
		Parameter param = new WhereParam(Order.class, DB_KEY_ORDER_ADMIN_ID,
				adminId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE, sort);

		return super.queryBy(request, param);
	}

	public Page<Order> queryByClientId(final int pageNumber, final BigInteger clientId)
			throws DataAccessException {
		Parameter param = new WhereParam(Order.class, DB_KEY_ORDER_CLIENT_ID,
				clientId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE, sort);

		return super.queryBy(request, param);
	}

	public Page<Order> queryByStatus(final int pageNumber, final BigInteger shopId,
			final OrderStatus status) throws DataAccessException {
		Parameter param_shop = new WhereParam(Order.class,
				DB_KEY_ORDER_SHOP_ID, shopId);
		Parameter param_status = new WhereParam(Order.class,
				DB_KEY_ORDER_STATUS, status.toString());
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE, sort);

		return super.queryBy(request, param_shop, param_status);
	}
}
