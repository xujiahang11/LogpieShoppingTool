package com.logpie.shopping.tool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logpie.shopping.tool.model.Category;
import com.logpie.shopping.tool.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository repository;

	public void createCategory(final String categoryName) {
		if (categoryName == null || categoryName == "") {
			return;
		}
		Category category = new Category(categoryName);
		// repository.create(category);
	}

	public List<Category> getAllCategoris() {
		return repository.queryAll(Category.class);
	}

	public Category getCategoryById(final String categoryNameId) {
		String[] args = new String[1];
		args[0] = categoryNameId;
		return repository.queryByPrimaryKey(Category.class, args);
	}
}
