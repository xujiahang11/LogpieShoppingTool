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
import com.logpie.shopping.tool.model.Brand;
import com.logpie.shopping.tool.service.BrandService;

@Controller
@LogEnvironment(classLevel = LogLevel.TRACE)
public class HomePageController {
	@Autowired
	BrandService service;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(HomePageController.class);

	@RequestMapping(path = "/greeting/{name}", method = RequestMethod.GET)
	public String greeting(@PathVariable("name") String name, Model model)
			throws InterruptedException {
		logger.trace("Request started...");

		List<Brand> list = service.getAllBrands();
		logger.debug("QueryAllBrands is done...");
		int i = 1;
		for (Brand brand : list) {
			logger.debug(i + ". " + brand.getBrandName());
			i++;
			Thread.sleep(500);
		}
		model.addAttribute("name", "world");
		logger.trace("Request done...");
		return "greeting";
	}
}
