package com.logpie.shopping.tool.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

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
		if (product == null) {
			logger.error("cannot find product");
			return null;
		}
		try {
			return repository.insert(product);
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateProduct(final Product product) {
		logger.trace("updateProduct service is started...");
		if (product == null) {
			logger.error("cannot find product");
			return;
		}
		repository.update(product);
	}

	public List<Product> getAllProducts() {
		logger.trace("QueryAllProducts service is started...");
		return repository.queryAll(Product.class, null);
	}

	public Product getProductById(final Long id) {
		logger.trace("QueryProductById service is started...");
		if (id == null) {
			logger.error("cannot find product id");
			return null;
		}
		return repository.queryById(Product.class, id);
	}

	public List<Product> getProductsByShopId(final Long shopId) {
		logger.trace("QueryProductsByShopId service is started...");
		if (shopId == null) {
			logger.error("cannot find Shop Id");
			return null;
		}
		return repository.queryByShopId(shopId);
	}

	public List<Product> getProductsByBrandId(final Long brandId) {
		logger.trace("QueryProductsByBrandId service is started...");
		if (brandId == null) {
			logger.error("cannot find Brand Id");
			return null;
		}
		return repository.queryByBrandId(brandId);
	}

	public List<Product> getProductsByCategoryId(final Long categoryId) {
		logger.trace("QueryProductsByCategoryId service is started...");
		if (categoryId == null) {
			logger.error("cannot find category Id");
			return null;
		}
		return repository.queryByCategoryId(categoryId);
	}
}
