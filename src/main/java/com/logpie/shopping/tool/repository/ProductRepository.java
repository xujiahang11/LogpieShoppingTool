package com.logpie.shopping.tool.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.logpie.shopping.tool.model.Brand;
import com.logpie.shopping.tool.model.Color;
import com.logpie.shopping.tool.model.Product;
import com.logpie.shopping.tool.model.Shop;
import com.logpie.shopping.tool.model.Size;
import com.logpie.shopping.tool.model.SubCategory;

@Repository
public class ProductRepository extends LogpieRepository<Product> {
	public static final String DB_TABLE_PRODUCT = "Product";
	public static final String DB_KEY_PRODUCT_ID = "ProductId";
	public static final String DB_KEY_PRODUCT_NAME = "ProductName";
	public static final String DB_KEY_PRODUCT_WEIGHT = "ProductWeight";
	public static final String DB_KEY_PRODUCT_POST_DATE = "ProductPostDate";
	public static final String DB_KEY_PRODUCT_BRAND_ID = "ProductBrandId";
	public static final String DB_KEY_PRODUCT_SUBCATEGORY_ID = "ProductSubCategoryId";
	public static final String DB_KEY_PRODUCT_COLOR_ID = "ProductColorId";
	public static final String DB_KEY_PRODUCT_SIZE_ID = "ProductSizeId";
	public static final String DB_KEY_PRODUCT_ORIGINAL_ID = "ProductOriginalId";
	public static final String DB_KEY_PRODUCT_SHOP_ID = "ProductShopId";

	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private SubCategoryRepository subcategoryRepository;
	@Autowired
	private ColorRepository colorRepository;
	@Autowired
	private SizeRepository sizeRepository;
	@Autowired
	private ShopRepository shopRepository;

	public List<Product> queryByShopId(final Long shopId)
			throws DataAccessException {
		return super.queryByForeignKey(Product.class, DB_KEY_PRODUCT_SHOP_ID,
				shopId, null);
	}

	public List<Product> queryByBrandId(final Long arg)
			throws DataAccessException {
		return super.queryByForeignKey(Product.class, DB_KEY_PRODUCT_BRAND_ID,
				arg, null);
	}

	public List<Product> queryByCategoryId(final Long arg)
			throws DataAccessException {
		List<Product> res = new ArrayList<Product>();
		List<SubCategory> list = subcategoryRepository.queryByCategoryId(arg);
		for (SubCategory subcategory : list) {
			res.addAll(super.queryByForeignKey(Product.class,
					DB_KEY_PRODUCT_SUBCATEGORY_ID, subcategory.getId(), null));
		}
		return res;
	}

	@Override
	public Product mapRow(final ResultSet rs, final int rowNum)
			throws SQLException {
		Long id = rs.getLong(DB_KEY_PRODUCT_ID);
		String name = rs.getString(DB_KEY_PRODUCT_NAME);
		Integer weight = rs.getInt(DB_KEY_PRODUCT_WEIGHT);
		Timestamp date = rs.getTimestamp(DB_KEY_PRODUCT_POST_DATE);
		Brand brand = brandRepository.mapRow(rs, rowNum);
		SubCategory subcategory = subcategoryRepository.mapRow(rs, rowNum);
		Color color = colorRepository.mapRow(rs, rowNum);
		Size size = sizeRepository.mapRow(rs, rowNum);
		String originalId = rs.getString(DB_KEY_PRODUCT_ORIGINAL_ID);
		Shop shop = shopRepository.mapRow(rs, rowNum);

		return new Product(id, name, weight, date, brand, subcategory, color,
				size, originalId, shop);
	}
}
