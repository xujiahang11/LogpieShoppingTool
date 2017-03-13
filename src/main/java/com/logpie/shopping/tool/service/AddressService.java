package com.logpie.shopping.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Address;
import com.logpie.shopping.tool.model.Client;
import com.logpie.shopping.tool.repository.AddressRepository;
import com.logpie.shopping.tool.repository.ClientRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository repository;
	@Autowired
	private ClientRepository clientRepository;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());

	public Long createAddress(final String address, final String recipentName,
			final String recipentPhone, final String clientId) {
		logger.trace("createAddress service is started...");
		if (address == null || address.isEmpty()) {
			logger.error("cannot find address");
			return null;
		}
		if (recipentName == null || recipentName.isEmpty()) {
			logger.error("cannot find recipent name");
			return null;
		}
		Client client = clientRepository.queryByID(Client.class, clientId);
		Address addr = new Address(address, recipentName, recipentPhone, client);
		return repository.create(addr);
	}

	public List<Address> getAllAddresses() {
		logger.trace("QueryAllSubCategories service is started...");
		return repository.queryAll(Address.class);
	}

	public Address getAddressById(final String addressId) {
		logger.trace("QueryAddressById service is started...");
		if (addressId == null || addressId.isEmpty()) {
			logger.error("cannot find address Id");
			return null;
		}
		return repository.queryByID(Address.class, addressId);
	}

	public List<Address> getAddressesByClientId(final String clientId) {
		logger.trace("QueryAddressesByCategoryId service is started...");
		if (clientId == null || clientId.isEmpty()) {
			logger.error("cannot find category Id");
			return null;
		}
		String[] args = new String[1];
		args[0] = clientId;
		return repository.queryByClientId(args);
	}
}
