package com.logpie.shopping.tool.model;

import com.logpie.framework.db.annotation.AutoGenerate;
import com.logpie.framework.db.annotation.AutoGenerate.AutoGenerateType;
import com.logpie.framework.db.annotation.Column;
import com.logpie.framework.db.annotation.Column.DataType;
import com.logpie.framework.db.annotation.ID;
import com.logpie.framework.db.annotation.Table;
import com.logpie.framework.db.basic.LogpieModel;
import com.logpie.shopping.tool.repository.AdminRepository;

@Table(name = AdminRepository.DB_TABLE_ADMIN)
public class Admin extends LogpieModel {
	@ID
	@AutoGenerate(strategy = AutoGenerateType.NumberAutoIncrement)
	@Column(name = AdminRepository.DB_KEY_ADMIN_ID, type = DataType.LONG)
	private Long adminId;

	@Column(name = AdminRepository.DB_KEY_ADMIN_USER_NAME, type = DataType.STRING)
	private String adminUserName;

	@Column(name = AdminRepository.DB_KEY_ADMIN_PASSWORD, type = DataType.STRING)
	private String adminPassword;

	@Column(name = AdminRepository.DB_KEY_ADMIN_PASSWORD_VERSION, type = DataType.INTEGER)
	private Integer adminPasswordVersion;

	@Column(name = AdminRepository.DB_KEY_ADMIN_NAME, type = DataType.STRING)
	private String adminName;

	@Column(name = AdminRepository.DB_KEY_ADMIN_PHONE, type = DataType.STRING)
	private String adminPhone;

	@Column(name = AdminRepository.DB_KEY_ADMIN_WECHAT, type = DataType.STRING)
	private String adminWechat;

	@Column(name = AdminRepository.DB_KEY_ADMIN_PROFIT_PERCENTAGE, type = DataType.FLOAT)
	private Float adminProfitPercentage;

	@Column(name = AdminRepository.DB_KEY_ADMIN_IS_SUPER_MANAGER, type = DataType.BOOLEAN)
	private Boolean adminIsSuperManager;

	/**
	 * constructor for creating an admin
	 * 
	 * @param userName
	 * @param password
	 * @param name
	 */
	public Admin(String userName, String password, String name) {
		this(null, userName, password, 1, name, null, null, 1.0F, false);
	}

	/**
	 * 
	 * @param adminId
	 * @param adminUserName
	 * @param adminPassword
	 * @param adminPasswordVersion
	 * @param adminName
	 * @param adminPhone
	 * @param adminWechat
	 * @param adminProfitPercentage
	 * @param adminIsSuperManager
	 */
	public Admin(Long adminId, String adminUserName, String adminPassword,
			Integer adminPasswordVersion, String adminName, String adminPhone,
			String adminWechat, Float adminProfitPercentage,
			Boolean adminIsSuperManager) {
		this.adminId = adminId;
		this.adminUserName = adminUserName;
		this.adminPassword = adminPassword;
		this.adminPasswordVersion = adminPasswordVersion;
		this.adminName = adminName;
		this.adminPhone = adminPhone;
		this.adminWechat = adminWechat;
		this.adminProfitPercentage = adminProfitPercentage;
		this.adminIsSuperManager = adminIsSuperManager;
	}

	public Long getAdminId() {
		return adminId;
	}

	public String getAdminUserName() {
		return adminUserName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public Integer getAdminPasswordVersion() {
		return adminPasswordVersion;
	}

	public String getAdminName() {
		return adminName;
	}

	public String getAdminPhone() {
		return adminPhone;
	}

	public String getAdminWechat() {
		return adminWechat;
	}

	public Float getAdminProfitPercentage() {
		return adminProfitPercentage;
	}

	public Boolean getAdminIsSuperManager() {
		return adminIsSuperManager;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public void setAdminPasswordVersion(Integer adminPasswordVersion) {
		this.adminPasswordVersion = adminPasswordVersion;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}

	public void setAdminWechat(String adminWechat) {
		this.adminWechat = adminWechat;
	}

	public void setAdminProfitPercentage(Float adminProfitPercentage) {
		this.adminProfitPercentage = adminProfitPercentage;
	}

	public void setAdminIsSuperManager(Boolean adminIsSuperManager) {
		this.adminIsSuperManager = adminIsSuperManager;
	}

}
