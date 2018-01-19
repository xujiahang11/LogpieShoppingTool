package com.logpie.shopping.tool.repository;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.HtmlUtils;

import com.logpie.dba.api.basic.Page;
import com.logpie.dba.api.basic.PageRequest;
import com.logpie.dba.api.basic.Pageable;
import com.logpie.dba.api.basic.Sort;
import com.logpie.dba.api.basic.Parameter;
import com.logpie.dba.api.basic.WhereParam;
import com.logpie.dba.api.repository.JDBCTemplateRepository;
import com.logpie.shopping.tool.model.Product;

@Repository
public class ProductRepository extends JDBCTemplateRepository<Product> {

	public static final Integer PAGE_SIZE = 20;

	public static final String DB_TABLE_PRODUCT = "Product";

	public static final String DB_KEY_PRODUCT_ID = "productId";
	public static final String DB_KEY_PRODUCT_NAME = "productName";
	public static final String DB_KEY_PRODUCT_POST_DATE = "productPostDate";
	public static final String DB_KEY_PRODUCT_BRAND_ID = "productBrandId";
	public static final String DB_KEY_PRODUCT_SUBCATEGORY_ID = "productSubCategoryId";
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

	public Page<Product> queryByShopId(final int pageNumber, final BigInteger shopId)
			throws DataAccessException {
		Parameter param = new WhereParam(Product.class, DB_KEY_PRODUCT_SHOP_ID,
				shopId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE, sort);

		return super.queryBy(request, param);
	}

	public Page<Product> queryByBrandId(final int pageNumber, final BigInteger brandId)
			throws DataAccessException {
		Parameter param = new WhereParam(Product.class,
				DB_KEY_PRODUCT_BRAND_ID, brandId);
		Pageable request = new PageRequest(pageNumber, PAGE_SIZE, sort);

		return super.queryBy(request, param);
	}
}
