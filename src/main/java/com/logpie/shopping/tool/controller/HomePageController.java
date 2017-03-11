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
import com.logpie.shopping.tool.service.ClientService;

@Controller
@LogEnvironment(classLevel = LogLevel.TRACE)
public class HomePageController {
	@Autowired
	private ClientService service;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(HomePageController.class);

	@RequestMapping(path = "/greeting/{name}", method = RequestMethod.GET)
	public String greeting(@PathVariable("name") String name, Model model)
			throws InterruptedException {
		logger.trace("Request started...");

		logger.debug("create started...");
		String clientName = "QiaoMengying";
		String clientPhone = "18626158611";
		service.createClient(clientName, clientPhone);
		logger.debug("create done...");

		logger.debug("get all client started...");
		Client c1 = service.getAllClients().get(0);
		logger.debug("client: " + c1.getClientName());
		logger.debug("get all client done...");

		logger.debug("get client by id started...");
		Client c2 = service.getClientById(String.valueOf(4));
		logger.debug("client: " + c2.getClientName());
		logger.debug("get client by id done...");

		model.addAttribute("name", "world");
		logger.trace("Request done...");
		return "greeting";
	}
}
