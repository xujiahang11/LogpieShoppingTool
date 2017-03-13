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
import com.logpie.shopping.tool.model.Address;
import com.logpie.shopping.tool.service.AddressService;

@Controller
@LogEnvironment(classLevel = LogLevel.TRACE)
public class HomePageController {
	@Autowired
	private AddressService service;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(HomePageController.class);

	@RequestMapping(path = "/greeting/{name}", method = RequestMethod.GET)
	public String greeting(@PathVariable("name") String name, Model model)
			throws InterruptedException {
		logger.trace("Request started...");

		Address addr1 = service.getAddressById("2");
		logger.debug("get address by id 1:" + addr1.getAddress());

		Address addr2 = service.getAllAddresses().get(0);
		logger.debug("get all address: index 0 " + addr2.getAddress());

		Address addr3 = service.getAddressesByClientId("1").get(0);
		logger.debug("get address by id 1:" + addr3.getAddress());

		model.addAttribute("name", "world");
		logger.trace("Request done...");
		return "greeting";
	}
}
