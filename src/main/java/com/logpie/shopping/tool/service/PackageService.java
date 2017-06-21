package com.logpie.shopping.tool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.logpie.framework.db.basic.Page;
import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Package;
import com.logpie.shopping.tool.model.Package.PackageStatus;
import com.logpie.shopping.tool.repository.PackageRepository;

@Service
public class PackageService {
	@Autowired
	private PackageRepository repository;
	@Autowired
	private ExpressService deliveryService;
	@Autowired
	private ClientService clientService;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());

	public Long createPackage(final Package pack) {
		logger.trace("createPackage service is started...");
		Assert.notNull(pack, "Package must not be null");

		try {
			return repository.insert(pack);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updatePackage(final Package pack) {
		logger.trace("updatePackage service is started...");
		Assert.notNull(pack, "Package must not be null");

		repository.update(pack);
	}

	public Package getPackageById(final Long id) {
		logger.trace("QueryPackageById service is started...");
		Assert.notNull(id, "Id must not be null");

		return repository.queryOne(id);
	}

	public Page<Package> getPackagesByShopId(final int pageNumber,
			final Long shopId) {
		logger.trace("QueryPackagesByShopId service is started...");
		Assert.notNull(shopId, "Shop Id must not be null");

		return repository.queryByShopId(pageNumber, shopId);
	}

	public Page<Package> getPackagesByClientId(final int pageNumber,
			final Long clientId) {
		logger.trace("QueryPackagesByClientId service is started...");
		Assert.notNull(clientId, "Client Id must not be null");

		return repository.queryByClientId(pageNumber, clientId);
	}

	public Page<Package> getPackagesByStatus(final int pageNumber,
			final Long shopId, final PackageStatus status) {
		Assert.notNull(shopId, "Shop Id must not be null");
		Assert.notNull(status, "Package status must not be null");

		return repository.queryByStatus(pageNumber, shopId, status);
	}
}
