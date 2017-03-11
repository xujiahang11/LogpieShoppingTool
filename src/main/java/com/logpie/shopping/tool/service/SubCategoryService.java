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

	public void createSubCategory(final String subCategoryName,
			final String categoryId) {
		logger.trace("CreateSubCategory service is started...");
		if (subCategoryName == null || subCategoryName.isEmpty()) {
			logger.error("cannot find sub-category name");
			return;
		}
		if (categoryId == null || categoryId.isEmpty()) {
			logger.error("cannot find category id");
			return;
		}
		Category category = categoryService.getCategoryById(categoryId);
		SubCategory subcategory = new SubCategory(subCategoryName, category);
		repository.create(subcategory);
	}

	public List<SubCategory> getAllSubCategories() {
		logger.trace("QueryAllSubCategories service is started...");
		return repository.queryAll(SubCategory.class);
	}

	public SubCategory getSubCategoryById(final String subCategoryId) {
		logger.trace("QuerySubCategoryById service is started...");
		if (subCategoryId == null || subCategoryId.isEmpty()) {
			logger.error("cannot find sub-category Id");
			return null;
		}
		String[] args = new String[1];
		args[0] = subCategoryId;
		return repository.queryByPrimaryKey(SubCategory.class, args);
	}

	public List<SubCategory> getSubCategoriesByCategoryId(
			final String categoryId) {
		logger.trace("QuerySubCategoryByCategoryId service is started...");
		if (categoryId == null || categoryId.isEmpty()) {
			logger.error("cannot find category Id");
			return null;
		}
		String[] args = new String[1];
		args[0] = categoryId;
		return repository.queryByCategoryId(args);
	}
}
