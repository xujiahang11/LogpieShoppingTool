package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.logpie.shopping.tool.model.Admin;
import com.logpie.shopping.tool.model.Shop;

@Repository
public class AdminRepository extends LogpieRepository<Admin> {

	public static final String DB_TABLE_ADMIN = "Admin";

	public static final String DB_KEY_ADMIN_ID = "AdminId";
	public static final String DB_KEY_ADMIN_NAME = "AdminName";
	public static final String DB_KEY_ADMIN_PHONE = "AdminPhone";
	public static final String DB_KEY_ADMIN_WECHAT = "AdminWechat";
	public static final String DB_KEY_ADMIN_PROFIT_PERCENTAGE = "AdminProfitPercentage";
	public static final String DB_KEY_ADMIN_SHOP_ID = "AdminShopId";

	@Autowired
	private ShopRepository shopRepository;

	public List<Admin> queryByShopId(final Long shopId)
			throws DataAccessException {
		return super.queryByForeignKey(Admin.class, DB_KEY_ADMIN_SHOP_ID,
				shopId, null);
	}

	@Override
	public Admin mapRow(final ResultSet rs, final int rowNum)
			throws SQLException {
		Long adminId = rs.getLong(DB_KEY_ADMIN_ID);
		String adminName = rs.getString(DB_KEY_ADMIN_NAME);
		String adminPhone = rs.getString(DB_KEY_ADMIN_PHONE);
		String adminWechat = rs.getString(DB_KEY_ADMIN_WECHAT);
		Float adminProfitPercentage = rs
				.getFloat(DB_KEY_ADMIN_PROFIT_PERCENTAGE);
		Shop adminShop = shopRepository.mapRow(rs, rowNum);
		return new Admin(adminId, adminName, adminPhone, adminWechat,
				adminProfitPercentage, adminShop);
	}

}
