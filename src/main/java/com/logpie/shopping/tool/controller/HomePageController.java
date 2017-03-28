package com.logpie.shopping.tool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.logpie.framework.log.annotation.LogEnvironment;
import com.logpie.framework.log.annotation.LogEnvironment.LogLevel;
import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Product;
import com.logpie.shopping.tool.service.BrandService;
import com.logpie.shopping.tool.service.ColorService;
import com.logpie.shopping.tool.service.ProductService;
import com.logpie.shopping.tool.service.SizeService;
import com.logpie.shopping.tool.service.SubCategoryService;

@Controller
@LogEnvironment(classLevel = LogLevel.TRACE)
public class HomePageController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ColorService colorService;
	@Autowired
	private SizeService sizeService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private SubCategoryService subcategoryService;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(HomePageController.class);

	@RequestMapping(path = "/greeting/{name}", method = RequestMethod.GET)
	public String greeting(@PathVariable("name") String name, Model model)
			throws InterruptedException {
		logger.trace("Request started...");

		productService.createProduct("test_product", 1L, 1L);

		List<Product> list = productService.getProductsByBrandId(1L);
		logger.debug("Brand id is 1: " + list.size());

		List<Product> list2 = productService.getProductsByCategoryId(1L);
		logger.debug("Category id is 1: " + list2.size());

		model.addAttribute("name", "world");
		logger.trace("Request done...");
		return "greeting";
	}
}
