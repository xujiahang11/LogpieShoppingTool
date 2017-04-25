package com.logpie.shopping.tool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.logpie.framework.db.basic.Page;
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
		Assert.notNull(addr, "Address must not be null");

		try {
			return repository.insert(addr);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateAddress(final Address addr) {
		logger.trace("updateAddress service is started...");
		Assert.notNull(addr, "Address must not be null");

		try {
			repository.update(addr);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Address getAddressById(final Long id) {
		logger.trace("QueryAddressById service is started...");
		Assert.notNull(id, "Address id must not be null");

		try {
			return repository.queryOne(id);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Page<Address> getAddressesByShopId(final int pageNumber,
			final Long shopId) {
		logger.trace("QueryAddressesByShopId service is started...");
		Assert.notNull(shopId, "Shop id must not be null");

		try {
			return repository.queryByShopId(pageNumber, shopId);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Page<Address> getAddressesByClientId(final int pageNumber,
			final Long clientId) {
		logger.trace("QueryAddressesByClientId service is started...");
		Assert.notNull(clientId, "Client id must not be null");

		try {
			return repository.queryByClientId(pageNumber, clientId);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
