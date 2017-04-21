package com.logpie.shopping.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.logpie.framework.log.annotation.LogEnvironment;
import com.logpie.framework.log.annotation.LogEnvironment.LogLevel;
import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Shop;
import com.logpie.shopping.tool.service.AddressService;
import com.logpie.shopping.tool.service.AdminService;
import com.logpie.shopping.tool.service.BrandService;
import com.logpie.shopping.tool.service.ClientService;
import com.logpie.shopping.tool.service.OrderService;
import com.logpie.shopping.tool.service.PackageService;
import com.logpie.shopping.tool.service.ProductService;
import com.logpie.shopping.tool.service.ShopService;
import com.logpie.shopping.tool.service.SubCategoryService;

@Controller
@LogEnvironment(classLevel = LogLevel.TRACE)
public class HomePageController {
	@Autowired
	private ShopService shopService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private PackageService packageService;
	@Autowired
	private ProductService productService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private SubCategoryService subcategoryService;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(HomePageController.class);

	@ModelAttribute
	public void preHandle() {
		logger.trace("pre handle started...");

	}

	@RequestMapping(path = "/greeting/{name}", method = RequestMethod.GET)
	public String greeting(@PathVariable("name") String name, Model model)
			throws InterruptedException {
		logger.trace("Request started...");

		Shop shop_2 = shopService.getShopByPath("logpie");
		logger.debug("get shop - " + shop_2.getPath());

		model.addAttribute("name", "world");
		logger.trace("Request done...");
		return "greeting";
	}
}
