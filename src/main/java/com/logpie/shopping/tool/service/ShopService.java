package com.logpie.shopping.tool.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Shop;
import com.logpie.shopping.tool.repository.ShopRepository;

@Service
public class ShopService {
	@Autowired
	private ShopRepository repository;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());

	public Long createShop(final Shop shop) {
		logger.trace("createShop service is started...");
		if (shop == null) {
			logger.error("cannot find shop object");
			return null;
		}
		try {
			return repository.insert(shop);
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateShop(final Shop shop) {
		logger.trace("updateShop service is started...");
		if (shop == null) {
			logger.error("cannot find shop object");
			return;
		}
		repository.update(shop);
	}

	public List<Shop> getAllShops() {
		logger.trace("QueryAllShops service is started...");
		return repository.queryAll(Shop.class);
	}

	public Shop getShopById(final Long id) {
		logger.trace("QueryShopById service is started...");
		if (id == null) {
			logger.error("cannot find shop id");
			return null;
		}
		return repository.queryById(Shop.class, id);
	}

	public Shop getShopByPath(final String path) {
		logger.trace("QueryShopByPath service is started...");
		if (path == null || path.isEmpty()) {
			logger.error("cannot find shop path");
			return null;
		}
		return repository.queryByPath(path);
	}
}
