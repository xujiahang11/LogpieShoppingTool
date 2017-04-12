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
import com.logpie.shopping.tool.model.Order;
import com.logpie.shopping.tool.model.Order.OrderStatus;
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

	@RequestMapping(path = "/greeting/{name}", method = RequestMethod.GET)
	public String greeting(@PathVariable("name") String name, Model model)
			throws InterruptedException {
		logger.trace("Request started...");

		Shop shop_1 = shopService.getShopByPath("");
		logger.debug("should not find shop - " + shop_1);

		Shop shop_2 = shopService.getShopByPath("logpie");
		logger.debug("get shop - " + shop_2.getPath());
		// Client client = clientService.getClientById(1L);

		List<Order> list_1 = orderService.getOrdersByShopId(shop_2.getId(),
				false);
		for (Order o : list_1) {
			logger.debug("order list: " + o.getId() + " " + o.getStatus() + " "
					+ o.getShop().getName());
		}

		List<Order> list_2 = orderService.getOrdersByStatus(1L,
				OrderStatus.TO_BE_SHIPPED, false);
		for (Order o : list_2) {
			logger.debug("order list: " + o.getId() + " " + o.getStatus() + " "
					+ o.getShop().getName());
		}

		List<Order> list_3 = orderService.getStockOrders(1L, true);
		for (Order o : list_3) {
			logger.debug("order list: " + o.getId() + " is stock: "
					+ o.getIsStock() + " " + o.getShop().getName());
		}

		model.addAttribute("name", "world");
		logger.trace("Request done...");
		return "greeting";
	}
}
