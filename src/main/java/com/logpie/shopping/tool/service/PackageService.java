package com.logpie.shopping.tool.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Client;
import com.logpie.shopping.tool.model.Delivery;
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

	public Long createPackage(String packageReceiver, String packageDestination) {
		logger.trace("createPackage service is started...");
		if (packageReceiver == null || packageReceiver.isEmpty()) {
			logger.error("cannot find receiver of package");
			return null;
		}
		if (packageDestination == null || packageDestination.isEmpty()) {
			logger.error("cannot find destination of package");
			return null;
		}
		Package p = new Package(packageReceiver, packageDestination);
		return repository.insert(p);
	}

	public Long createPackage(Delivery packageIntDelivery,
			String packageIntTrackingNumber, Delivery packageDomDelivery,
			String packageDomTrackingNumber, Client packageClient,
			String packageReceiver, String packageDestination,
			Boolean packageIsDirectDelivered, Timestamp packageDate,
			Integer packageWeight, Float packageShippingFee,
			Float packageAdditionalCustomTaxFee,
			Float packageAdditionalInsuranceFee, PackageStatus packageStatus,
			String packageNote) {
		logger.trace("createPackage service is started...");
		if (packageReceiver == null || packageReceiver.isEmpty()) {
			logger.error("cannot find receiver of package");
			return null;
		}
		if (packageDestination == null || packageDestination.isEmpty()) {
			logger.error("cannot find destination of package");
			return null;
		}
		if (packageStatus == null) {
			packageStatus = PackageStatus.TO_BE_SHIPPED;
		}
		Package p = new Package(null, packageIntDelivery,
				packageIntTrackingNumber, packageDomDelivery,
				packageDomTrackingNumber, packageClient, packageReceiver,
				packageDestination, packageIsDirectDelivered, packageDate,
				packageWeight, packageShippingFee,
				packageAdditionalCustomTaxFee, packageAdditionalInsuranceFee,
				packageStatus, packageNote);
		return repository.insert(p);
	}

	public Long createPackage(Long packageIntDeliveryId,
			String packageIntTrackingNumber, Long packageDomDeliveryId,
			String packageDomTrackingNumber, Long packageClientId,
			String packageReceiver, String packageDestination,
			Boolean packageIsDirectDelivered, Timestamp packageDate,
			Integer packageWeight, Float packageShippingFee,
			Float packageAdditionalCustomTaxFee,
			Float packageAdditionalInsuranceFee, PackageStatus packageStatus,
			String packageNote) {
		if (packageIntDeliveryId != null) {
			logger.error("cannot find international delivery id");
		}
		if (packageDomDeliveryId != null) {
			logger.error("cannot find domestic delivery id");
		}
		if (packageClientId != null) {
			logger.error("cannot find client id");
		}
		return createPackage(
				deliveryService.getDeliveryById(packageIntDeliveryId),
				packageIntTrackingNumber,
				deliveryService.getDeliveryById(packageDomDeliveryId),
				packageDomTrackingNumber,
				clientService.getClientById(packageClientId), packageReceiver,
				packageDestination, packageIsDirectDelivered, packageDate,
				packageWeight, packageShippingFee,
				packageAdditionalCustomTaxFee, packageAdditionalInsuranceFee,
				packageStatus, packageNote);
	}

	public List<Package> getAllPackages() {
		logger.trace("QueryAllPackages service is started...");
		return repository.queryAll(Package.class);
	}

	public Package getPackageById(final Long packageId) {
		logger.trace("QueryPackageById service is started...");
		if (packageId == null) {
			logger.error("cannot find package Id");
			return null;
		}
		return repository.queryByID(Package.class, packageId);
	}

	public List<Package> getPackagesByClientId(final Long clientId) {
		logger.trace("QueryPackagesByClientId service is started...");
		if (clientId == null) {
			logger.error("cannot find category Id");
			return null;
		}
		return repository.queryByClientId(clientId);
	}
}
