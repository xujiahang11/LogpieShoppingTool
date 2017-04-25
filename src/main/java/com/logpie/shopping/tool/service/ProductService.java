package com.logpie.shopping.tool.service;

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
	private ColorService colorService;
	@Autowired
	private SizeService sizeService;

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

		return repository.queryOne(id);
	}

	public Page<Product> getProductsByShopId(final int pageNumber,
			final Long shopId) {
		logger.trace("QueryProductsByShopId service is started...");
		Assert.notNull(shopId, "Shop id must not be null");

		return repository.queryByShopId(pageNumber, shopId);
	}

	public Page<Product> getProductsByBrandId(final int pageNumber,
			final Long brandId) {
		logger.trace("QueryProductsByBrandId service is started...");
		Assert.notNull(brandId, "Brand id must not be null");

		return repository.queryByBrandId(pageNumber, brandId);
	}

	public Page<Product> getProductsByCategoryId(final int pageNumber,
			final Long categoryId) {
		logger.trace("QueryProductsByCategoryId service is started...");
		Assert.notNull(categoryId, "Category id must not be null");

		return repository.queryByCategoryId(pageNumber, categoryId);
	}
}
