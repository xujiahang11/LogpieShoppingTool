package com.logpie.shopping.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public Long createClient(final String ClientName, final String ClientPhone) {
		logger.trace("createClient service is started...");
		if (ClientName == null || ClientName.isEmpty() || ClientPhone == null
				|| ClientPhone.isEmpty()) {
			logger.error("cannot find client name or client phone");
			return null;
		}
		Client client = new Client(ClientName, ClientPhone);
		return repository.insert(client);
	}

	public List<Client> getAllClients() {
		logger.trace("QueryAllClients service is started...");
		return repository.query(Client.class, null);
	}

	public Client getClientById(final Long clientId) {
		logger.trace("QueryClientById service is started...");
		if (clientId == null) {
			logger.error("cannot find brand Id");
			return null;
		}
		return repository.queryByID(Client.class, clientId);
	}
}
