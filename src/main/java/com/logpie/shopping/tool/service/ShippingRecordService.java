package com.logpie.shopping.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.ShippingRecord;
import com.logpie.shopping.tool.repository.ShippingRecordRepository;

@Service
public class ShippingRecordService {

	@Autowired
	private ShippingRecordRepository repository;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());

	public Long createRecord(final ShippingRecord record) {
		logger.trace("createRecord service is started...");
		Assert.notNull(record, "Record must not be null");

		try {
			return repository.insert(record);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateRecord(final ShippingRecord record) {
		logger.trace("updateRecord service is started...");
		Assert.notNull(record, "Record must not be null");

		repository.update(record);
	}

	public ShippingRecord getRecordById(final Long id) {
		logger.trace("QueryRecordById service is started...");
		Assert.notNull(id, "Id must not be null");

		return repository.queryOne(id);
	}

	public List<ShippingRecord> getRecordsByPackageId(final Long packageId) {
		logger.trace("getRecordsByPackageId service is started...");
		Assert.notNull(packageId, "Package id must not be null");

		return repository.queryByPackageId(packageId);
	}

	public List<ShippingRecord> getRecordsByTransactionId(final Long tId) {
		logger.trace("getRecordsByTransactionId service is started...");
		Assert.notNull(tId, "Transaction id must not be null");

		return repository.queryByTransactionId(tId);
	}
}
