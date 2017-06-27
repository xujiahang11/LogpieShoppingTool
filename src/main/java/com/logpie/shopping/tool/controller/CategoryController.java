package com.logpie.shopping.tool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logpie.shopping.tool.model.Category;
import com.logpie.shopping.tool.model.Shop;
import com.logpie.shopping.tool.model.SubCategory;
import com.logpie.shopping.tool.service.CategoryService;
import com.logpie.shopping.tool.service.ShopService;
import com.logpie.shopping.tool.service.SubCategoryService;

@Controller
@RequestMapping("/{shopPath}/products")
public class CategoryController {

	@Autowired
	private CategoryService service;
	@Autowired
	private SubCategoryService subcategoryService;
	@Autowired
	private ShopService shopService;

	@RequestMapping(path = "/category", method = RequestMethod.GET)
	public @ResponseBody List<Category> getCategoryListByAJAX(
			@PathVariable final String shopPath) {
		List<Category> categories = service.getCategoriesByShopId(shopService
				.getShopByPath(shopPath).getId());
		return categories;
	}

	@RequestMapping(path = "/category/create", method = RequestMethod.POST)
	public @ResponseBody String createCategoryByAJAX(
			@PathVariable final String shopPath,
			@RequestBody final Category category) {
		Shop shop = shopService.getShopByPath(shopPath);
		category.setShop(shop);
		service.createCategory(category);

		return "success";
	}

	@RequestMapping(path = "/category/{cid}/subcategory/create", method = RequestMethod.POST)
	public @ResponseBody String createSubCategoryByAJAX(
			@PathVariable final Long cid,
			@RequestBody final SubCategory subCategory) {
		Category category = service.getCategoryById(cid);
		subCategory.setCategory(category);
		subcategoryService.createSubCategory(subCategory);

		return subCategory.getName();
	}
}
