package com.logpie.shopping.tool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logpie.framework.db.basic.Page;
import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Brand;
import com.logpie.shopping.tool.model.Category;
import com.logpie.shopping.tool.model.Product;
import com.logpie.shopping.tool.model.ProductConfig;
import com.logpie.shopping.tool.model.Shop;
import com.logpie.shopping.tool.service.BrandService;
import com.logpie.shopping.tool.service.CategoryService;
import com.logpie.shopping.tool.service.ProductConfigService;
import com.logpie.shopping.tool.service.ProductService;
import com.logpie.shopping.tool.service.ShopService;

@Controller
@RequestMapping("/{shopPath}/products")
public class ProductController {

	@Autowired
	private ProductService service;
	@Autowired
	private ProductConfigService configService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ShopService shopService;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(this.getClass());


	@RequestMapping(path = "", method = RequestMethod.GET)
	public String getDefaultPage() {
		return "redirect:/{shopPath}/products/1";
	}

	@RequestMapping(path = "/{page}", method = RequestMethod.GET)
	public String getPage(@PathVariable final String shopPath, final Model model) {
		Shop shop = shopService.getShopByPath(shopPath);
		model.addAttribute("shop", shop);
		return "product/page";
	}

	@RequestMapping(path = "/{page}/list", method = RequestMethod.GET)
	public String getAll(@PathVariable final String shopPath,
			@PathVariable final Integer page, final Model model) {
		Shop shop = shopService.getShopByPath(shopPath);
		Page<Product> products = service
				.getProductsByShopId(page, shop.getId());
		model.addAttribute("shop", shop);
		model.addAttribute("products", products);

		return "product/list_products :: #table_products";
	}

	@RequestMapping(path = "/id/{id}", method = RequestMethod.GET)
	public @ResponseBody Product getByAJAX(@PathVariable final Long id) {
		return null;
	}

	@RequestMapping(path = "/config/{cid}", method = RequestMethod.GET)
	public @ResponseBody ProductConfig getProductConfigById(
			@PathVariable final Long cid) {
		return configService.getProductConfigById(cid);
	}

	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public @ResponseBody List<Product> searchByName(
			@RequestParam("name") final String s) {
		logger.debug("Get search data: " + s);
		return service.getProductsByName(s);
	}

	@RequestMapping(path = "/creationForm", method = RequestMethod.GET)
	public String getCreationForm(@PathVariable final String shopPath,
			final Model model) {
		Shop shop = shopService.getShopByPath(shopPath);
		List<Brand> brands = brandService.getBrandsByShopId(shop.getId());
		List<Category> categories = categoryService.getCategoriesByShopId(shop
				.getId());

		model.addAttribute("shop", shop);
		model.addAttribute("new_product", new Product());
		model.addAttribute("brands", brands);
		model.addAttribute("categories", categories);
		return "product/form_create_product :: #popup_create_product";
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public String create(@PathVariable final String shopPath,
			@ModelAttribute("new_product") final Product product,
			@ModelAttribute("new_configs") final List<ProductConfig> configs) {
		Shop shop = shopService.getShopByPath(shopPath);
		product.setShop(shop);
		service.createProduct(product);

		if (configs.size() > 0) {
			for (ProductConfig config : configs) {
				config.setProduct(product);
				configService.createProductConfig(config);
			}
		}
		return "redirect:/{shopPath}/products/1";
	}
}
