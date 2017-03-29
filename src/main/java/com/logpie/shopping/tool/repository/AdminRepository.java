package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.logpie.shopping.tool.model.Admin;

@Repository
public class AdminRepository extends LogpieRepository<Admin> {

	public static final String DB_TABLE_ADMIN = "Admin";

	public static final String DB_KEY_ADMIN_ID = "AdminId";
	public static final String DB_KEY_ADMIN_USER_NAME = "AdminUserName";
	public static final String DB_KEY_ADMIN_PASSWORD = "AdminPassword";
	public static final String DB_KEY_ADMIN_PASSWORD_VERSION = "AdminPasswordVersion";
	public static final String DB_KEY_ADMIN_NAME = "AdminName";
	public static final String DB_KEY_ADMIN_PHONE = "AdminPhone";
	public static final String DB_KEY_ADMIN_WECHAT = "AdminWechat";
	public static final String DB_KEY_ADMIN_PROFIT_PERCENTAGE = "AdminProfitPercentage";
	public static final String DB_KEY_ADMIN_IS_SUPER_MANAGER = "AdminIsSuperManager";

	@Override
	public Admin mapRow(final ResultSet rs, final int rowNum)
			throws SQLException {
		Long adminId = rs.getLong(DB_KEY_ADMIN_ID);
		String adminUserName = rs.getString(DB_KEY_ADMIN_USER_NAME);
		String adminPassword = rs.getString(DB_KEY_ADMIN_PASSWORD);
		Integer adminPasswordVersion = rs.getInt(DB_KEY_ADMIN_PASSWORD_VERSION);
		String adminName = rs.getString(DB_KEY_ADMIN_NAME);
		String adminPhone = rs.getString(DB_KEY_ADMIN_PHONE);
		String adminWechat = rs.getString(DB_KEY_ADMIN_WECHAT);
		Float adminProfitPercentage = rs
				.getFloat(DB_KEY_ADMIN_PROFIT_PERCENTAGE);
		Boolean adminIsSuperManager = rs
				.getBoolean(DB_KEY_ADMIN_IS_SUPER_MANAGER);
		return new Admin(adminId, adminUserName, adminPassword,
				adminPasswordVersion, adminName, adminPhone, adminWechat,
				adminProfitPercentage, adminIsSuperManager);
	}

}
