package com.logpie.shopping.tool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Shop;
import com.logpie.shopping.tool.service.ShopService;

@Controller
public class ShopController {
	@Autowired
	private ShopService service;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(AddressController.class);

	// 返回值为void的方法均会被提前调用，可以传任何参数如@RequestParam@Session等
	@ModelAttribute
	public void preHandle() {
		logger.trace("pre handle started...");
	}

	@RequestMapping(path = "/shop", method = RequestMethod.GET)
	public String getAll(final Model model) {
		List<Shop> shops = service.getAllShops();
		model.addAttribute("shops", shops);
		return "shops";
	}

	@RequestMapping(path = "/shop/add", method = RequestMethod.GET)
	public String add(final Model model) {
		model.addAttribute("shop", new Shop());
		return "form_shop";
	}

	@RequestMapping(path = "/shop/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("shop") final Shop shop) {
		service.createShop(shop);
		String path = shop.getPath();
		return "redirect:/shop/" + path;
	}

	@RequestMapping(path = "/{shopPath}", method = RequestMethod.GET)
	public String getByPath(final Model model) {
		return "redirect:/{shopPath}/address";
	}

}
