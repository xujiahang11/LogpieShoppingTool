package com.logpie.shopping.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logpie.framework.db.basic.Page;
import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Product;
import com.logpie.shopping.tool.model.Shop;
import com.logpie.shopping.tool.service.ProductService;
import com.logpie.shopping.tool.service.ShopService;

@Controller
@RequestMapping("/{shopPath}/products")
public class ProductController {

	@Autowired
	private ProductService service;
	@Autowired
	private ShopService shopService;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());

	@RequestMapping(path = "/{page}", method = RequestMethod.GET)
	public String getPage(@PathVariable final String shopPath, final Model model) {
		Shop shop = shopService.getShopByPath(shopPath);
		model.addAttribute("shop", shop);

		return "product/page";
	}

	@RequestMapping(path = "/{page}/list", method = RequestMethod.GET)
	public String getAllByAJAX(@PathVariable final String shopPath,
			@PathVariable final Integer page, final Model model) {
		Shop shop = shopService.getShopByPath(shopPath);
		Page<Product> products = service
				.getProductsByShopId(page, shop.getId());
		model.addAttribute("shop", shop);
		model.addAttribute("products", products);

		return "product/product_list :: #table_products";
	}

	@RequestMapping(path = "/id/{id}", method = RequestMethod.GET)
	public @ResponseBody Product getByAJAX(@PathVariable final Long id) {
		return null;
	}
}
