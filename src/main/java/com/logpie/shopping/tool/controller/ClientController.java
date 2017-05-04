package com.logpie.shopping.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.logpie.framework.db.basic.Page;
import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Client;
import com.logpie.shopping.tool.model.Shop;
import com.logpie.shopping.tool.service.ClientService;
import com.logpie.shopping.tool.service.ShopService;

@Controller
@RequestMapping("/{shopPath}/clients")
public class ClientController {

	@Autowired
	private ClientService service;
	@Autowired
	private ShopService shopService;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(AddressController.class);

	@RequestMapping(path = "/{page}", method = RequestMethod.GET)
	public String getAll(@PathVariable final String shopPath,
			@PathVariable final Integer page, final Model model) {
		Shop shop = shopService.getShopByPath(shopPath);
		Page<Client> clients = service.getClientsByShopId(page, shop.getId());

		model.addAttribute("shop", shop);
		model.addAttribute("clients", clients);
		model.addAttribute("new_client", new Client());
		return "clients";
	}

	@RequestMapping(path = "/{page}", method = RequestMethod.POST)
	public String add(@PathVariable final String shopPath,
			@ModelAttribute("new_client") final Client client) {
		Shop shop = shopService.getShopByPath(shopPath);
		client.setShop(shop);
		service.createClient(client);
		return "redirect:/{shopPath}/clients/1";
	}
}
