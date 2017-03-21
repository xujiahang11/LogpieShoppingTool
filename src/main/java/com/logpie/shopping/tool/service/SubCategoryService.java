package com.logpie.shopping.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Category;
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

	public Long createSubCategory(final String subCategoryName,
			final Long categoryId) {
		logger.trace("CreateSubCategory service is started...");
		if (subCategoryName == null || subCategoryName.isEmpty()) {
			logger.error("cannot find sub-category name");
			return null;
		}
		if (categoryId == null) {
			logger.error("cannot find category id");
			return null;
		}
		Category category = categoryService.getCategoryById(categoryId);
		SubCategory subcategory = new SubCategory(subCategoryName, category);
		return repository.insert(subcategory);
	}

	public List<SubCategory> getAllSubCategories() {
		logger.trace("QueryAllSubCategories service is started...");
		return repository.queryAll(SubCategory.class);
	}

	public SubCategory getSubCategoryById(final Long subCategoryId) {
		logger.trace("QuerySubCategoryById service is started...");
		if (subCategoryId == null) {
			logger.error("cannot find sub-category Id");
			return null;
		}
		return repository.queryByID(SubCategory.class, subCategoryId);
	}

	public List<SubCategory> getSubCategoriesByCategoryId(final Long categoryId) {
		logger.trace("QuerySubCategoriesByCategoryId service is started...");
		if (categoryId == null) {
			logger.error("cannot find category Id");
			return null;
		}
		return repository.queryByCategoryId(categoryId);
	}
}
