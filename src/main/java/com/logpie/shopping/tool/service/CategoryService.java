package com.logpie.shopping.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Category;
import com.logpie.shopping.tool.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository repository;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());

	public Long createCategory(final String categoryName) {
		logger.trace("createCategory service is started...");
		if (categoryName == null || categoryName.isEmpty()) {
			logger.error("cannot find brand name");
			return null;
		}
		Category category = new Category(categoryName);
		return repository.insert(category);
	}

	public List<Category> getAllCategoris() {
		logger.trace("QueryAllCategories service is started...");
		return repository.query(Category.class, null);
	}

	public Category getCategoryById(final Long categoryId) {
		logger.trace("QueryCategoryById service is started...");
		if (categoryId == null) {
			logger.error("cannot find category Id");
			return null;
		}
		return repository.queryByID(Category.class, categoryId);
	}
}
