package com.logpie.shopping.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Color;
import com.logpie.shopping.tool.repository.ColorRepository;

@Service
public class ColorService {
	@Autowired
	private ColorRepository repository;
	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());

	public Long createColor(final Color color) {
		logger.trace("createColor service is started...");
		Assert.notNull(color, "Color must not be null");

		try {
			return repository.insert(color);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateColor(final Color color) {
		logger.trace("updateColor service is started...");
		Assert.notNull(color, "Color must not be null");

		repository.update(color);
	}

	public Color getColorById(final Long id) {
		logger.trace("QueryColorById service is started...");
		Assert.notNull(id, "Id must not be null");

		return repository.queryOne(id);
	}

	public List<Color> getColorsByShopId(final Long shopId) {
		logger.trace("QueryColorsByShopId service is started...");
		Assert.notNull(shopId, "Shop id must not be null");

		return repository.queryByShopId(shopId);
	}
}
