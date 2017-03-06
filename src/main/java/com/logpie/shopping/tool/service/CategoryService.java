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

	public void createCategory(final String categoryName) {
		logger.trace("createCategory service is started...");

		if (categoryName == null || categoryName == "") {
			logger.error("cannot find brand name");
			return;
		}
		Category category = new Category(categoryName);
		repository.create(category);
	}

	public List<Category> getAllCategoris() {
		logger.trace("QueryAllCategories service is started...");

		return repository.queryAll(Category.class);
	}

	public Category getCategoryById(final String categoryId) {
		logger.trace("QueryCategoryById service is started...");

		if (categoryId == null || categoryId == "") {
			logger.error("cannot find category Id");
			return null;
		}
		String[] args = new String[1];
		args[0] = categoryId;
		return repository.queryByPrimaryKey(Category.class, args);
	}
}
