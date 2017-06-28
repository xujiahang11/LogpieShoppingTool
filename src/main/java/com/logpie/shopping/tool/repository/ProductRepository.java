package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.HtmlUtils;

import com.logpie.framework.db.basic.Page;
import com.logpie.framework.db.basic.PageRequest;
import com.logpie.framework.db.basic.Pageable;
import com.logpie.framework.db.basic.Parameter;
import com.logpie.framework.db.basic.Sort;
import com.logpie.framework.db.basic.WhereParam;
import com.logpie.framework.db.repository.JDBCTemplateRepository;
import com.logpie.shopping.tool.model.Product;

@Repository
public class ProductRepository extends JDBCTemplateRepository<Product> {

	public static final Integer PAGE_SIZE = 20;

	public static final String DB_TABLE_PRODUCT = "Product";

	public static final String DB_KEY_PRODUCT_ID = "productId";
	public static final String DB_KEY_PRODUCT_NAME = "productName";
	public static final String DB_KEY_PRODUCT_WEIGHT = "productWeight";
	public static final String DB_KEY_PRODUCT_POST_DATE = "productPostDate";
	public static final String DB_KEY_PRODUCT_BRAND_ID = "productBrandId";
	public static final String DB_KEY_PRODUCT_SUBCATEGORY_ID = "productSubCategoryId";
	public static final String DB_KEY_PRODUCT_ORIGINAL_ID = "productOriginalId";
	public static final String DB_KEY_PRODUCT_NOTE = "productNote";
	public static final String DB_KEY_PRODUCT_SHOP_ID = "productShopId";

	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private SubCategoryRepository subcategoryRepository;
	@Autowired
	private ShopRepository shopRepository;

	private Sort sort;

	public ProductRepository() {
		super(Product.class);
		// TODO
		sort = new Sort(Sort.Direction.DESC, DB_KEY_PRODUCT_POST_DATE);
	}

	public List<Product> queryByName(final String name) {
		HtmlUtils.htmlEscape(name);
		String reg = "%" + name + "%";
		Parameter param = new WhereParam(Product.class, DB_KEY_PRODUCT_NAME,
				"LIKE", reg);
		return (List<Product>) super.queryBy(param);
	}

	public Page<Product> queryByShopId(final int pageNumber, final Long shopId)
			throws DataAccessException {
		Parameter param = new WhereParam(Product.class, DB_KEY_PRODUCT_SHOP_ID,
				shopId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE, sort);

		return super.queryBy(request, param);
	}

	public Page<Product> queryByBrandId(final int pageNumber, final Long brandId)
			throws DataAccessException {
		Parameter param = new WhereParam(Product.class,
				DB_KEY_PRODUCT_BRAND_ID, brandId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE, sort);

		return super.queryBy(request, param);
	}

	@Override
	public Product mapRow(final ResultSet rs, final int rowNum)
			throws SQLException {
		Product product = new Product();
		product.setId(rs.getLong(DB_KEY_PRODUCT_ID));
		product.setName(rs.getString(DB_KEY_PRODUCT_NAME));
		product.setWeight(rs.getInt(DB_KEY_PRODUCT_WEIGHT));
		product.setPostDate(rs.getTimestamp(DB_KEY_PRODUCT_POST_DATE));
		product.setBrand(brandRepository.mapRow(rs, rowNum));
		product.setSubCategory(subcategoryRepository.mapRow(rs, rowNum));
		product.setOriginalId(rs.getString(DB_KEY_PRODUCT_ORIGINAL_ID));
		product.setNote(rs.getString(DB_KEY_PRODUCT_NOTE));
		product.setShop(shopRepository.mapRow(rs, rowNum));

		return product;
	}
}
