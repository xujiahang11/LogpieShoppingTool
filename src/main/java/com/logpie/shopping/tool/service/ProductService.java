package com.logpie.shopping.tool.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.logpie.framework.db.basic.Page;
import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Product;
import com.logpie.shopping.tool.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repository;
	@Autowired
	private BrandService brandService;
	@Autowired
	private SubCategoryService subcategoryService;
	@Autowired
	private ProductConfigService configService;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());

	public Long createProduct(final Product product) {
		logger.trace("createProduct service is started...");
		Assert.notNull(product, "Product must not be null");

		try {
			return repository.insert(product);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateProduct(final Product product) {
		logger.trace("updateProduct service is started...");
		Assert.notNull(product, "Product must not be null");

		repository.update(product);
	}

	public Product getProductById(final Long id) {
		logger.trace("QueryProductById service is started...");
		Assert.notNull(id, "Id must not be null");

		Product p = repository.queryOne(id);
		addProductConfigs(p);
		return p;
	}

	public List<Product> getProductsByName(final String name) {
		logger.trace("QueryProductByName service is started...");
		Assert.notNull(name, "Name must not be null");
		if (name.equals("")) {
			return new ArrayList<Product>();
		}
		List<Product> list = repository.queryByName(name);
		addProductConfigs(list);
		return list;
	}

	public Page<Product> getProductsByShopId(final int pageNumber,
			final Long shopId) {
		logger.trace("QueryProductsByShopId service is started...");
		Assert.notNull(shopId, "Shop id must not be null");

		Page<Product> page = repository.queryByShopId(pageNumber, shopId);
		addProductConfigs(page);
		return page;
	}

	public Page<Product> getProductsByBrandId(final int pageNumber,
			final Long brandId) {
		logger.trace("QueryProductsByBrandId service is started...");
		Assert.notNull(brandId, "Brand id must not be null");

		Page<Product> page = repository.queryByBrandId(pageNumber, brandId);
		addProductConfigs(page);
		return page;
	}

	private void addProductConfigs(final Iterable<Product> ite) {
		Assert.notNull(ite, "List must not be null");

		for (Product product : ite) {
			addProductConfigs(product);
		}
	}

	private void addProductConfigs(final Product product) {
		Assert.notNull(product, "Product must not be null");

		product.setConfigs(configService.getProductConfigsByProductId(product
				.getId()));
	}
}
