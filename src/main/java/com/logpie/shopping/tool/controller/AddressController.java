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
import com.logpie.shopping.tool.model.Address;
import com.logpie.shopping.tool.service.AddressService;
import com.logpie.shopping.tool.service.ClientService;
import com.logpie.shopping.tool.service.ShopService;

@Controller
@RequestMapping("/{shopPath}/address#{page}")
public class AddressController {

	@Autowired
	private AddressService service;
	@Autowired
	private ClientService clientService;
	@Autowired
	private ShopService shopService;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(AddressController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String getAll(@PathVariable final String shopPath,
			@PathVariable final int page, final Model model) {
		Long shopId = shopService.getShopByPath(shopPath).getId();
		Page<Address> addresses = service.getAddressesByShopId(page, shopId);
		model.addAttribute("addresses", addresses);
		return "addresses";
	}

	@RequestMapping(path = "/user_{clientId}", method = RequestMethod.GET)
	public String getByUser(@PathVariable final Long clientId,
			@PathVariable final int page, final Model model) {
		Page<Address> addresses = service
				.getAddressesByClientId(page, clientId);
		model.addAttribute("addresses", addresses);
		return "addresses";
	}

	@RequestMapping(path = "/user_{clientId}/add", method = RequestMethod.GET)
	public String add(final Model model) {
		model.addAttribute("addr", new Address());
		return "form_address";
	}

	@RequestMapping(path = "/user_{clientId}/add", method = RequestMethod.POST)
	public String add(@PathVariable final Long clientId,
			@ModelAttribute("addr") final Address addr) {
		addr.setClient(clientService.getClientById(clientId));
		service.createAddress(addr);
		return "redirect:/user_{clientId}";
	}

	@RequestMapping(path = "/user_{clientId}/{addressId}", method = RequestMethod.GET)
	public String edit(@PathVariable final Long addressId, final Model model) {
		Address addr = service.getAddressById(addressId);
		model.addAttribute("addr", addr);
		return "form_address";
	}

	@RequestMapping(path = "/user_{clientId}/{addressId}", method = RequestMethod.POST)
	public String edit(@ModelAttribute("addr") final Address addr) {
		service.updateAddress(addr);
		return "redirect:/user_{clientId}";
	}
}
