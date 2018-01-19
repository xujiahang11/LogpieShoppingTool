package com.logpie.shopping.tool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.logpie.dba.api.basic.Page;
import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Client;
import com.logpie.shopping.tool.repository.ClientRepository;

import java.math.BigInteger;

@Service
public class ClientService {
	@Autowired
	private ClientRepository repository;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());

	public Client createClient(final Client client) {
		logger.trace("createClient service is started...");
		Assert.notNull(client, "Client must not be null");

		try {
			return repository.insert(client);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateClient(final Client client) {
		logger.trace("updateClient service is started...");
		Assert.notNull(client, "Client must not be null");

		repository.update(client);
	}

	public Client getClientById(final BigInteger id) {
		logger.trace("QueryClientById service is started...");
		Assert.notNull(id, "Id must not be null");

		return repository.queryOne(id);
	}

	public Page<Client> getClientsByShopId(final int pageNumber,
			final BigInteger shopId) {
		logger.trace("QueryClientsByShopId service is started...");
		Assert.notNull(shopId, "Shop id must not be null");

		return repository.queryByShopId(pageNumber, shopId);
	}
}
