package com.logpie.shopping.tool.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

	public Shop createShop(final Shop shop) {
		logger.trace("createShop service is started...");
		Assert.notNull(shop, "Shop must not be null");

		try {
			return repository.insert(shop);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateShop(final Shop shop) {
		logger.trace("updateShop service is started...");
		Assert.notNull(shop, "Shop must not be null");

		repository.update(shop);
	}

	public List<Shop> getAllShops() {
		logger.trace("QueryAllShops service is started...");
		return (List<Shop>) repository.queryAll();
	}

	public Shop getShopById(final BigInteger id) {
		logger.trace("QueryShopById service is started...");
		Assert.notNull(id, "Id must not be null");

		return repository.queryOne(id);
	}

	public Shop getShopByPath(final String path) {
		logger.trace("QueryShopByPath service is started...");
		Assert.hasLength(path, "Path must have length");

		return repository.queryByPath(path);
	}
}
