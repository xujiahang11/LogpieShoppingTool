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

	public void createClient(final String ClientName, final String ClientPhone) {
		logger.trace("createClient service is started...");
		if (ClientName == null || ClientName.isEmpty() || ClientPhone == null
				|| ClientPhone.isEmpty()) {
			logger.error("cannot find client name or client phone");
			return;
		}
		Client client = new Client(ClientName, ClientPhone);
		repository.create(client);
	}

	public List<Client> getAllClients() {
		logger.trace("QueryAllClients service is started...");
		return repository.queryAll(Client.class);
	}

	public Client getClientById(final String clientId) {
		logger.trace("QueryClientById service is started...");
		if (clientId == null || clientId.isEmpty()) {
			logger.error("cannot find brand Id");
			return null;
		}
		return repository.queryByID(Client.class, clientId);
	}
}
