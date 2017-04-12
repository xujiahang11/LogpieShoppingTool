package com.logpie.shopping.tool.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Address;
import com.logpie.shopping.tool.repository.AddressRepository;

@Service
public class AddressService {
	@Autowired
	private AddressRepository repository;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());

	public Long createAddress(final Address addr) {
		logger.trace("createAddress service is started...");
		if (addr == null) {
			logger.error("cannot find address object");
			return null;
		}
		try {
			return repository.insert(addr);
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateAddress(final Address addr) {
		logger.trace("updateAddress service is started...");
		if (addr == null) {
			logger.error("cannot find address object");
			return;
		}
		try {
			repository.update(addr);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Address> getAllAddresses() {
		logger.trace("QueryAllAddresses service is started...");
		try {
			return repository.queryAll(Address.class);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Address getAddressById(final Long id) {
		logger.trace("QueryAddressById service is started...");
		if (id == null) {
			logger.error("cannot find address id");
			return null;
		}
		try {
			return repository.queryById(Address.class, id);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Address> getAddressesByShopId(final Long shopId) {
		logger.trace("QueryAddressesByShopId service is started...");
		try {
			return repository.queryByShopId(shopId);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Address> getAddressesByClientId(final Long clientId) {
		logger.trace("QueryAddressesByClientId service is started...");
		if (clientId == null) {
			logger.error("cannot find client Id");
			return null;
		}
		try {
			return repository.queryByClientId(clientId);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
