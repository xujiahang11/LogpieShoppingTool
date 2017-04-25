package com.logpie.shopping.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Size;
import com.logpie.shopping.tool.repository.SizeRepository;

@Service
public class SizeService {
	@Autowired
	private SizeRepository repository;
	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());

	public Long createSize(final Size size) {
		logger.trace("createSize service is started...");
		Assert.notNull(size, "Size must not be null");

		try {
			return repository.insert(size);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateSize(final Size size) {
		logger.trace("updateSize service is started...");
		Assert.notNull(size, "Size must not be null");

		repository.update(size);
	}

	public Size getSizeById(final Long id) {
		logger.trace("QuerySizeById service is started...");
		Assert.notNull(id, "Id must not be null");

		return repository.queryOne(id);
	}

	public List<Size> getSizesByShopId(final Long shopId) {
		logger.trace("QuerySizesByShopId service is started...");
		Assert.notNull(shopId, "Shop id must not be null");

		return repository.queryByShopId(shopId);
	}
}
