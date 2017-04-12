package com.logpie.shopping.tool.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

	public Long createCategory(final Category category) {
		logger.trace("createCategory service is started...");
		if (category == null) {
			logger.error("cannot find category");
			return null;
		}
		try {
			return repository.insert(category);
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void updateCategory(final Category category) {
		logger.trace("updateCategory service is started...");
		if (category == null) {
			logger.error("cannot find category");
			return;
		}
		repository.update(category);
	}

	public List<Category> getAllCategoris() {
		logger.trace("QueryAllCategories service is started...");
		return repository.queryAll(Category.class, null);
	}

	public Category getCategoryById(final Long id) {
		logger.trace("QueryCategoryById service is started...");
		if (id == null) {
			logger.error("cannot find category id");
			return null;
		}
		return repository.queryById(Category.class, id);
	}

	public List<Category> getCategoriesByShopId(final Long shopId) {
		logger.trace("QueryCategoriesByShopId service is started...");
		if (shopId == null) {
			logger.error("cannot find shop id");
			return null;
		}
		return repository.queryByShopId(shopId);
	}
}
