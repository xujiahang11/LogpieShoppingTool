package com.logpie.shopping.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Admin;
import com.logpie.shopping.tool.repository.AdminRepository;

@Service
public class AdminService {
	@Autowired
	private AdminRepository repository;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());

	public Long createAdmin(final String userName, final String password,
			final String name) {
		logger.trace("createAdmin service is started...");
		if (userName == null || userName.isEmpty() || password == null
				|| password.isEmpty() || name == null || name.isEmpty()) {
			logger.error("cannot find admin username or admin password or admin nickname");
			return null;
		}
		Admin admin = new Admin(userName, password, name);
		return repository.insert(admin);
	}

	public List<Admin> getAllAdmins() {
		logger.trace("QueryAllAdmins service is started...");
		return repository.queryAll(Admin.class, null);
	}

	public Admin getAdminById(final Long adminId) {
		logger.trace("QueryAdminById service is started...");
		if (adminId == null) {
			logger.error("cannot find admin Id");
			return null;
		}
		return repository.queryByID(Admin.class, adminId);
	}
}
