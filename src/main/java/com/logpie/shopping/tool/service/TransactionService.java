package com.logpie.shopping.tool.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Transaction;
import com.logpie.shopping.tool.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository repository;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());

	public Transaction createTransaction(final Transaction t) {
		logger.trace("createTransaction service is started...");
		Assert.notNull(t, "Transaction must not be null");

		try {
			return repository.insert(t);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateTransaction(final Transaction t) {
		logger.trace("updateTransaction service is started...");
		Assert.notNull(t, "Transaction must not be null");

		repository.update(t);
	}

	public Transaction getTransactionById(final BigInteger id) {
		logger.trace("QueryTransactionById service is started...");
		Assert.notNull(id, "Id must not be null");

		return repository.queryOne(id);
	}

	public List<Transaction> getTransactionsByOrderId(final BigInteger orderId) {
		logger.trace("queryTransactionsByOrderId service is started...");
		Assert.notNull(orderId, "Order id must not be null");

		return repository.queryByOrderId(orderId);
	}
}
