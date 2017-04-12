package com.logpie.shopping.tool.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

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
		if (size == null) {
			logger.error("cannot find size");
			return null;
		}
		try {
			return repository.insert(size);
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Size> getAllSizes() {
		logger.trace("QueryAllSizes service is started...");
		return repository.queryAll(Size.class, null);
	}

	public Size getSizeById(final Long id) {
		logger.trace("QuerySizeById service is started...");
		if (id == null) {
			logger.error("cannot find size id");
			return null;
		}
		return repository.queryById(Size.class, id);
	}

	public List<Size> getSizesByShopId(final Long shopId) {
		logger.trace("QuerySizesByShopId service is started...");
		if (shopId == null) {
			logger.error("cannot find shop Id");
			return null;
		}
		return repository.queryByShopId(shopId);
	}
}
