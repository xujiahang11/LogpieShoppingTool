package com.logpie.shopping.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public Long createColor(final String colorName) {
		logger.trace("createColor service is started...");
		if (colorName == null || colorName.isEmpty()) {
			logger.error("cannot find color name");
			return null;
		}
		Color color = new Color(colorName);
		return repository.insert(color);
	}

	public List<Color> getAllColors() {
		logger.trace("QueryAllColors service is started...");
		return repository.queryAll(Color.class, null);
	}

	public Color getColorById(final Long colorId) {
		logger.trace("QueryColorById service is started...");
		if (colorId == null) {
			logger.error("cannot find color Id");
			return null;
		}
		return repository.queryByID(Color.class, colorId);
	}
}
