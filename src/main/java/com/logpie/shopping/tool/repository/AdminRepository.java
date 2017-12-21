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
import com.logpie.shopping.tool.model.Admin;

@Repository
public class AdminRepository extends JDBCTemplateRepository<Admin> {

	public static final Integer PAGE_SIZE = 20;

	public static final String DB_TABLE_ADMIN = "Admin";

	public static final String DB_KEY_ADMIN_ID = "adminId";
	public static final String DB_KEY_ADMIN_NAME = "adminName";
	public static final String DB_KEY_ADMIN_PHONE = "adminPhone";
	public static final String DB_KEY_ADMIN_EMAIL = "adminEmail";
	public static final String DB_KEY_ADMIN_PROFIT_PERCENTAGE = "profitPercentage";
	public static final String DB_KEY_ADMIN_SHOP_ID = "adminShopId";

	@Autowired
	private ShopRepository shopRepository;

	public AdminRepository() {
		super(Admin.class);
	}

	public Page<Admin> queryByShopId(final int pageNumber, final Long shopId)
			throws DataAccessException {
		Parameter param = new WhereParam(Admin.class, DB_KEY_ADMIN_SHOP_ID,
				shopId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE);

		return super.queryBy(request, param);
	}
}
