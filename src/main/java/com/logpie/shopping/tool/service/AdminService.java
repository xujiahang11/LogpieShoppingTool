package com.logpie.shopping.tool.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

	public Long createAdmin(final Admin admin) {
		logger.trace("createAdmin service is started...");
		if (admin == null) {
			logger.error("cannot find admin");
			return null;
		}
		try {
			return repository.insert(admin);
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateAdmin(final Admin admin) {
		logger.trace("updateAdmin service is started...");
		if (admin == null) {
			logger.error("cannot find admin");
			return;
		}
		repository.update(admin);
	}

	public List<Admin> getAllAdmins() {
		logger.trace("QueryAllAdmins service is started...");
		return repository.queryAll(Admin.class, null);
	}

	public Admin getAdminById(final Long id) {
		logger.trace("QueryAdminById service is started...");
		if (id == null) {
			logger.error("cannot find admin id");
			return null;
		}
		return repository.queryById(Admin.class, id);
	}

	public List<Admin> getAdminsByShopId(final Long shopId) {
		logger.trace("QueryAdminsByShopId service is started...");
		if (shopId == null) {
			logger.error("cannot find shop id");
			return null;
		}
		return repository.queryByShopId(shopId);
	}
}
