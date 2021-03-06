package com.logpie.shopping.tool.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.SubCategory;
import com.logpie.shopping.tool.repository.SubCategoryRepository;

@Service
public class SubCategoryService {
	@Autowired
	private SubCategoryRepository repository;
	@Autowired
	private CategoryService categoryService;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());

	public SubCategory createSubCategory(final SubCategory subCategory) {
		logger.trace("createSubCategory service is started...");
		Assert.notNull(subCategory, "Subcategory must not be null");

		try {
			return repository.insert(subCategory);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateSubCategory(final SubCategory subCategory) {
		logger.trace("updateSubCategory service is started...");
		Assert.notNull(subCategory, "Subcategory must not be null");

		repository.update(subCategory);
	}

	public SubCategory getSubCategoryById(final BigInteger id) {
		logger.trace("QuerySubCategoryById service is started...");
		Assert.notNull(id, "Id must not be null");

		return repository.queryOne(id);
	}

	public List<SubCategory> getSubCategoriesByCategoryId(final BigInteger categoryId) {
		logger.trace("QuerySubCategoriesByCategoryId service is started...");
		Assert.notNull(categoryId, "Category id must not be null");

		return repository.queryByCategoryId(categoryId);
	}
}
