package com.logpie.shopping.tool.service;

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

	public Long createPackage(final Long packageIntDeliveryId,
			final String packageIntTrackingNumber,
			final Long packageDomDeliveryId,
			final String packageDomTrackingNumber, final Long packageClientId,
			final String packageReceiver, final String packageDestination,
			final Boolean packageIsDirectDelivered,
			final Integer packageWeight, final Float packageShippingFee,
			final Float packageAdditionalCustomTaxFee,
			final Float packageAdditionalInsuranceFee,
			final PackageStatus packageStatus, final String packageNote) {
		return createPackage(
				deliveryService.getDeliveryById(packageIntDeliveryId),
				packageIntTrackingNumber,
				deliveryService.getDeliveryById(packageDomDeliveryId),
				packageDomTrackingNumber,
				clientService.getClientById(packageClientId), packageReceiver,
				packageDestination, packageIsDirectDelivered, packageWeight,
				packageShippingFee, packageAdditionalCustomTaxFee,
				packageAdditionalInsuranceFee, packageStatus, packageNote);
	}

	public Long createPackage(final Delivery packageIntDelivery,
			final String packageIntTrackingNumber,
			final Delivery packageDomDelivery,
			final String packageDomTrackingNumber, final Client packageClient,
			final String packageReceiver, final String packageDestination,
			final Boolean packageIsDirectDelivered,
			final Integer packageWeight, final Float packageShippingFee,
			final Float packageAdditionalCustomTaxFee,
			final Float packageAdditionalInsuranceFee,
			final PackageStatus packageStatus, final String packageNote) {
		logger.trace("createPackage service is started...");
		if (packageClient == null
				&& (packageReceiver == null || packageReceiver.isEmpty())) {
			logger.error("cannot find receiver of package");
			return null;
		}
		if (packageDestination == null || packageDestination.isEmpty()) {
			logger.error("cannot find destination of package");
			return null;
		}
		// pre-handle initial values of some properties
		String receiver = packageClient == null ? packageReceiver
				: packageClient.getClientName();
		PackageStatus status = packageStatus == null ? PackageStatus.TO_BE_SHIPPED
				: packageStatus;
		Package p = new Package(null, packageIntDelivery,
				packageIntTrackingNumber, packageDomDelivery,
				packageDomTrackingNumber, packageClient, receiver,
				packageDestination, packageIsDirectDelivered, null,
				packageWeight, packageShippingFee,
				packageAdditionalCustomTaxFee, packageAdditionalInsuranceFee,
				status, packageNote);
		return repository.insert(p);
	}

	public List<Package> getAllPackages(final boolean isAscendingDate) {
		logger.trace("QueryAllPackages service is started...");
		return repository.queryAll(isAscendingDate);
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
