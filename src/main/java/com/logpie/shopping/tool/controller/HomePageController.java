package com.logpie.shopping.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@RequestMapping(path = "/testing/shop", method = RequestMethod.GET)
	public String test(final Model model) {
		logger.trace("Request started...");

		Shop ashop = shopService.getShopById(4L);
		logger.debug("get shop - " + shopService.getShopById(4L).getPath());

		ashop.setName("update_shop_0424");
		shopService.updateShop(ashop);

		Shop shop = shopService.getShopByPath("logpie");
		logger.debug("get shop id- " + shop.getId());

		logger.trace("Request done...");

		model.addAttribute("name", shop.getName());
		return "greeting";
	}
}
