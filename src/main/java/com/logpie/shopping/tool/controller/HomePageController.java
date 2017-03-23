package com.logpie.shopping.tool.controller;

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
import com.logpie.shopping.tool.model.Client;
import com.logpie.shopping.tool.model.Delivery;
import com.logpie.shopping.tool.service.ClientService;
import com.logpie.shopping.tool.service.DeliveryService;
import com.logpie.shopping.tool.service.PackageService;

@Controller
@LogEnvironment(classLevel = LogLevel.TRACE)
public class HomePageController {
	@Autowired
	private PackageService packageService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private DeliveryService deliveryService;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(HomePageController.class);

	@RequestMapping(path = "/greeting/{name}", method = RequestMethod.GET)
	public String greeting(@PathVariable("name") String name, Model model)
			throws InterruptedException {
		logger.trace("Request started...");

		deliveryService.createDelivery("test_int_delivery", true);
		deliveryService.createDelivery("test_dom_delivery", false);
		Delivery mTestIntDelivery = deliveryService.getDeliveryById(1L);
		Delivery mTestDomDelivery = deliveryService.getDeliveryById(2L);
		Client client = clientService.getClientById(4L);
		packageService.createPackage(mTestIntDelivery, null, mTestDomDelivery,
				null, client, "test_recipient", "test_destination", false,
				null, 500, 115.5f, null, null, null, null);

		model.addAttribute("name", "world");
		logger.trace("Request done...");
		return "greeting";
	}
}
