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
import com.logpie.shopping.tool.model.SubCategory;
import com.logpie.shopping.tool.service.SubCategoryService;

@Controller
@LogEnvironment(classLevel = LogLevel.TRACE)
public class HomePageController {
	@Autowired
	private SubCategoryService service;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(HomePageController.class);

	@RequestMapping(path = "/greeting/{name}", method = RequestMethod.GET)
	public String greeting(@PathVariable("name") String name, Model model)
			throws InterruptedException {
		logger.trace("Request started...");

		List<SubCategory> list = service.getSubCategoriesByCategoryId("1");
		logger.debug("Query is done...");
		for (SubCategory s : list) {
			logger.debug("SubCategory: " + s.getSubCategoryName()
					+ ", Category: " + s.getCategory().getCategoryName());
		}
		model.addAttribute("name", "world");
		logger.trace("Request done...");
		return "greeting";
	}

	class LogpieThread implements Runnable {
		private LogpieLogger logger = LogpieLoggerFactory
				.getLogger(LogpieThread.class);
		private Thread preThread;

		public LogpieThread(Thread t) {
			preThread = t;
		}

		@Override
		public void run() {
			try {
				LogpieLoggerFactory.mergeLog(preThread);
				logger.info("A new sub thread started...");
				Thread.currentThread().sleep(600);
				logger.info("A new sub thread done...");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
