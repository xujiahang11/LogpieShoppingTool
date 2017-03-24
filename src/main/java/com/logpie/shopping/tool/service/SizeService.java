package com.logpie.shopping.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public Long createSize(final String sizeName) {
		logger.trace("createSize service is started...");
		if (sizeName == null || sizeName.isEmpty()) {
			logger.error("cannot find size name");
			return null;
		}
		Size size = new Size(sizeName);
		return repository.insert(size);
	}

	public List<Size> getAllSizes() {
		logger.trace("QueryAllSizes service is started...");
		return repository.query(Size.class, null);
	}

	public Size getSizeById(final Long sizeId) {
		logger.trace("QuerySizeById service is started...");
		if (sizeId == null) {
			logger.error("cannot find size Id");
			return null;
		}
		return repository.queryByID(Size.class, sizeId);
	}
}
