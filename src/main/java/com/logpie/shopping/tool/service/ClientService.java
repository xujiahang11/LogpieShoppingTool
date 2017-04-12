package com.logpie.shopping.tool.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Client;
import com.logpie.shopping.tool.repository.ClientRepository;

@Service
public class ClientService {
	@Autowired
	private ClientRepository repository;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());

	public Long createClient(final Client client) {
		logger.trace("createClient service is started...");
		if (client == null) {
			logger.error("cannot find client");
			return null;
		}
		try {
			return repository.insert(client);
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateClient(final Client client) {
		logger.trace("updateClient service is started...");
		if (client == null) {
			logger.error("cannot find client");
			return;
		}
		repository.update(client);
	}

	public List<Client> getAllClients() {
		logger.trace("QueryAllClients service is started...");
		return repository.queryAll(Client.class, null);
	}

	public Client getClientById(final Long id) {
		logger.trace("QueryClientById service is started...");
		if (id == null) {
			logger.error("cannot find client id");
			return null;
		}
		return repository.queryById(Client.class, id);
	}

	public List<Client> getClientsByShopId(final Long shopId) {
		logger.trace("QueryClientsByShopId service is started...");
		if (shopId == null) {
			logger.error("cannot find shop Id");
			return null;
		}
		return repository.queryByShopId(shopId);
	}
}
