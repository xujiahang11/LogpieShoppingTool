package com.logpie.shopping.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.logpie.framework.db.basic.Page;
import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Category;
import com.logpie.shopping.tool.repository.CategoryRepository;
import com.logpie.shopping.tool.repository.SubCategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	@Autowired
	private SubCategoryRepository subRepository;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());

	public Long createCategory(final Category category) {
		logger.trace("createCategory service is started...");
		Assert.notNull(category, "Category must not be null");

		try {
			return repository.insert(category);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateCategory(final Category category) {
		logger.trace("updateCategory service is started...");
		Assert.notNull(category, "Category must not be null");

		repository.update(category);
	}

	public Category getCategoryById(final Long id) {
		logger.trace("QueryCategoryById service is started...");
		Assert.notNull(id, "Id must not be null");

		Category c = repository.queryOne(id);
		addSubCategoryList(c);
		return c;
	}

	public Page<Category> getCategoriesByShopId(final int pageNumber,
			final Long shopId) {
		logger.trace("QueryCategoriesByShopId service is started...");
		Assert.notNull(shopId, "Shop id must not be null");

		Page<Category> res = repository.queryByShopId(pageNumber, shopId);
		for (Category c : res) {
			addSubCategoryList(c);
		}
		return res;
	}

	public List<Category> getCategoriesByShopId(final Long shopId) {
		logger.trace("QueryCategoriesByShopId service is started...");
		Assert.notNull(shopId, "Shop id must not be null");

		List<Category> res = repository.queryByShopId(shopId);
		for (Category c : res) {
			addSubCategoryList(c);
		}
		return res;
	}

	private void addSubCategoryList(final Category category) {
		Assert.notNull(category, "Category must not be null");

		category.setSubcategories(subRepository.queryByCategoryId(category
				.getId()));
	}
}
