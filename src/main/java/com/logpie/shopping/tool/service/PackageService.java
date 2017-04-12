package com.logpie.shopping.tool.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

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
	private DeliveryService deliveryService;
	@Autowired
	private ClientService clientService;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());

	public Long createPackage(final Package pack) {
		logger.trace("createPackage service is started...");
		if (pack == null) {
			logger.error("cannot find package");
			return null;
		}
		try {
			return repository.insert(pack);
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updatePackage(final Package pack) {
		logger.trace("updatePackage service is started...");
		if (pack == null) {
			logger.error("cannot find package");
			return;
		}
		repository.update(pack);
	}

	public List<Package> getAllPackages(final boolean isAscendingDate) {
		logger.trace("QueryAllPackages service is started...");
		return repository.queryAll(isAscendingDate);
	}

	public Package getPackageById(final Long id) {
		logger.trace("QueryPackageById service is started...");
		if (id == null) {
			logger.error("cannot find package id");
			return null;
		}
		return repository.queryById(Package.class, id);
	}

	public List<Package> getPackagesByShopId(final Long shopId,
			final boolean isAscendingDate) {
		logger.trace("QueryPackagesByShopId service is started...");
		if (shopId == null) {
			logger.error("cannot find shop Id");
			return null;
		}
		return repository.queryByShopId(shopId, isAscendingDate);
	}

	public List<Package> getPackagesByClientId(final Long clientId,
			final boolean isAscendingDate) {
		logger.trace("QueryPackagesByClientId service is started...");
		if (clientId == null) {
			logger.error("cannot find client Id");
			return null;
		}
		return repository.queryByClientId(clientId, isAscendingDate);
	}

	public List<Package> getPackagesByStatus(final Long shopId,
			final PackageStatus status, final boolean isAscendingDate) {
		if (shopId == null) {
			logger.error("cannot find shop Id");
			return null;
		}
		if (status == null) {
			logger.error("cannot find package status");
			return null;
		}
		return repository.queryByStatus(shopId, status, isAscendingDate);
	}
}
