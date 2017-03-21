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
			final String recipentPhone, final Long clientId) {
		logger.trace("createAddress service is started...");
		if (address == null || address.isEmpty()) {
			logger.error("cannot find address");
			return null;
		}
		if (recipentName == null || recipentName.isEmpty()) {
			logger.error("cannot find recipent name");
			return null;
		}
		if (clientId == null) {
			logger.error("cannot find client id");
			return null;
		}
		Client client = clientRepository.queryByID(Client.class, clientId);
		Address addr = new Address(address, recipentName, recipentPhone, client);
		return repository.insert(addr);
	}

	public List<Address> getAllAddresses() {
		logger.trace("QueryAllAddresses service is started...");
		return repository.queryAll(Address.class);
	}

	public Address getAddressById(final Long addressId) {
		logger.trace("QueryAddressById service is started...");
		if (addressId == null) {
			logger.error("cannot find address Id");
			return null;
		}
		return repository.queryByID(Address.class, addressId);
	}

	public List<Address> getAddressesByClientId(final Long clientId) {
		logger.trace("QueryAddressesByClientId service is started...");
		if (clientId == null) {
			logger.error("cannot find client Id");
			return null;
		}
		return repository.queryByClientId(clientId);
	}
}
