package com.logpie.shopping.tool.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

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

	public Long createSubCategory(final SubCategory subCategory) {
		logger.trace("CreateSubCategory service is started...");
		if (subCategory == null) {
			logger.error("cannot find sub-category");
			return null;
		}
		try {
			return repository.insert(subCategory);
		} catch (DataAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<SubCategory> getAllSubCategories() {
		logger.trace("QueryAllSubCategories service is started...");
		return repository.queryAll(SubCategory.class, null);
	}

	public SubCategory getSubCategoryById(final Long id) {
		logger.trace("QuerySubCategoryById service is started...");
		if (id == null) {
			logger.error("cannot find sub-category id");
			return null;
		}
		return repository.queryById(SubCategory.class, id);
	}

	public List<SubCategory> getSubCategoriesByCategoryId(final Long categoryId) {
		logger.trace("QuerySubCategoriesByCategoryId service is started...");
		if (categoryId == null) {
			logger.error("cannot find category id");
			return null;
		}
		return repository.queryByCategoryId(categoryId);
	}

	public List<SubCategory> getSubCategoriesByShopId(final Long shopId) {
		logger.trace("QuerySubCategoriesByShopId service is started...");
		if (shopId == null) {
			logger.error("cannot find shop id");
			return null;
		}
		return repository.queryByShopId(shopId);
	}
}
