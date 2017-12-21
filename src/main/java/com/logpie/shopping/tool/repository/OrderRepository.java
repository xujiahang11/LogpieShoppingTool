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
import com.logpie.framework.db.basic.Sort;
import com.logpie.framework.db.basic.WhereParam;
import com.logpie.framework.db.repository.JDBCTemplateRepository;
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

	public Page<Order> queryByShopId(final int pageNumber, final Long shopId)
			throws DataAccessException {
		Parameter param = new WhereParam(Order.class, DB_KEY_ORDER_SHOP_ID,
				shopId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE, sort);

		return super.queryBy(request, param);
	}

	public Page<Order> queryByAdminId(final int pageNumber, final Long adminId)
			throws DataAccessException {
		Parameter param = new WhereParam(Order.class, DB_KEY_ORDER_ADMIN_ID,
				adminId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE, sort);

		return super.queryBy(request, param);
	}

	public Page<Order> queryByClientId(final int pageNumber, final Long clientId)
			throws DataAccessException {
		Parameter param = new WhereParam(Order.class, DB_KEY_ORDER_CLIENT_ID,
				clientId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE, sort);

		return super.queryBy(request, param);
	}

	public Page<Order> queryByStatus(final int pageNumber, final Long shopId,
			final OrderStatus status) throws DataAccessException {
		Parameter param_shop = new WhereParam(Order.class,
				DB_KEY_ORDER_SHOP_ID, shopId);
		Parameter param_status = new WhereParam(Order.class,
				DB_KEY_ORDER_STATUS, status.toString());
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE, sort);

		return super.queryBy(request, param_shop, param_status);
	}
}
