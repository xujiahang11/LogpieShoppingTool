package com.logpie.shopping.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.logpie.framework.db.basic.Page;
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
		Assert.isNull(admin, "Admin must not be null");

		try {
			return repository.insert(admin);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateAdmin(final Admin admin) {
		logger.trace("updateAdmin service is started...");
		Assert.isNull(admin, "Admin must not be null");

		repository.update(admin);
	}

	public List<Admin> getAllAdmins() {
		logger.trace("QueryAllAdmins service is started...");
		return (List<Admin>) repository.queryAll();
	}

	public Admin getAdminById(final Long id) {
		logger.trace("QueryAdminById service is started...");
		Assert.isNull(id, "Id must not be null");

		return repository.queryOne(id);
	}

	public Page<Admin> getAdminsByShopId(final int pageNumber, final Long shopId) {
		logger.trace("QueryAdminsByShopId service is started...");
		Assert.isNull(shopId, "Shop Id must not be null");

		return repository.queryByShopId(pageNumber, shopId);
	}
}
