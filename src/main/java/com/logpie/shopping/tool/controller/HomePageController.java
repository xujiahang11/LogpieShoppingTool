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
import com.logpie.shopping.tool.service.AdminService;
import com.logpie.shopping.tool.service.BrandService;
import com.logpie.shopping.tool.service.OrderService;
import com.logpie.shopping.tool.service.ProductService;
import com.logpie.shopping.tool.service.SubCategoryService;

@Controller
@LogEnvironment(classLevel = LogLevel.TRACE)
public class HomePageController {
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

		List<Order> list = orderService.getAllOrders(false);
		for (Order order : list) {
			logger.debug("order: " + order.getOrderId() + ", "
					+ order.getOrderProduct().getProductName() + ", "
					+ order.getOrderProductWeight() + "g, "
					+ order.getOrderProxy().getAdminName());
		}

		model.addAttribute("name", "world");
		logger.trace("Request done...");
		return "greeting";
	}
}
