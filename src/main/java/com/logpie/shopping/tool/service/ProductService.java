package com.logpie.shopping.tool.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Brand;
import com.logpie.shopping.tool.model.Color;
import com.logpie.shopping.tool.model.Product;
import com.logpie.shopping.tool.model.Size;
import com.logpie.shopping.tool.model.SubCategory;
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

	public Long createProduct(String productName, Long productBrandId,
			Long productSubCategoryId) {
		logger.trace("createProduct service is started...");
		if (productName == null || productName.isEmpty()) {
			logger.error("cannot find product name");
			return null;
		}
		if (productBrandId == null || productSubCategoryId == null) {
			logger.error("cannot find brand or subcategory of product");
			return null;
		}
		Brand brand = brandService.getBrandById(productBrandId);
		SubCategory subcategory = subcategoryService
				.getSubCategoryById(productSubCategoryId);
		Product product = new Product(productName, brand, subcategory);
		return repository.insert(product);
	}

	public Long createProduct(String productName, Integer productWeight,
			Timestamp productPostDate, Long productBrandId,
			Long productSubCategoryId, Long productColorId, Long productSizeId,
			String productOriginalId) {
		return createProduct(productName, productWeight,
				brandService.getBrandById(productBrandId),
				subcategoryService.getSubCategoryById(productSubCategoryId),
				colorService.getColorById(productColorId),
				sizeService.getSizeById(productSizeId), productOriginalId);
	}

	public Long createProduct(String productName, Integer productWeight,
			Brand productBrand, SubCategory productSubCategory,
			Color productColor, Size productSize, String productOriginalId) {
		logger.trace("createProduct service is started...");
		if (productName == null || productName.isEmpty()) {
			logger.error("cannot find product name");
			return null;
		}
		if (productBrand == null) {
			logger.error("cannot find brand of product");
			return null;
		}
		if (productSubCategory == null) {
			logger.error("cannot find sub-category of product");
			return null;
		}
		Product product = new Product(null, productName, productWeight, null,
				productBrand, productSubCategory, productColor, productSize,
				productOriginalId);
		return repository.insert(product);
	}

	public List<Product> getAllProducts() {
		logger.trace("QueryAllProducts service is started...");
		return repository.query(Product.class, null);
	}

	public Product getProductById(final Long productId) {
		logger.trace("QueryProductById service is started...");
		if (productId == null) {
			logger.error("cannot find product Id");
			return null;
		}
		return repository.queryByID(Product.class, productId);
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
