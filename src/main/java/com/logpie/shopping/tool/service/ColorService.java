package com.logpie.shopping.tool.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

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
		if (color == null) {
			logger.error("cannot find color");
			return null;
		}
		try {
			return repository.insert(color);
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateColor(final Color color) {
		logger.trace("updateColor service is started...");
		if (color == null) {
			logger.error("cannot find color");
			return;
		}
		repository.update(color);
	}

	public List<Color> getAllColors() {
		logger.trace("QueryAllColors service is started...");
		return repository.queryAll(Color.class, null);
	}

	public Color getColorById(final Long id) {
		logger.trace("QueryColorById service is started...");
		if (id == null) {
			logger.error("cannot find color id");
			return null;
		}
		return repository.queryById(Color.class, id);
	}

	public List<Color> getColorsByShopId(final Long shopId) {
		logger.trace("QueryColorsByShopId service is started...");
		if (shopId == null) {
			logger.error("cannot find shop Id");
			return null;
		}
		return repository.queryByShopId(shopId);
	}
}
