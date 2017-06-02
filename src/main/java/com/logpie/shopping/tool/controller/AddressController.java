package com.logpie.shopping.tool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logpie.framework.db.basic.Page;
import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;
import com.logpie.shopping.tool.model.Address;
import com.logpie.shopping.tool.service.AddressService;
import com.logpie.shopping.tool.service.ClientService;
import com.logpie.shopping.tool.service.ShopService;

@Controller
@RequestMapping("/{shopPath}/clients")
public class AddressController {

	@Autowired
	private AddressService service;
	@Autowired
	private ClientService clientService;
	@Autowired
	private ShopService shopService;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(AddressController.class);

	@RequestMapping(path = "/{page}/address", method = RequestMethod.GET)
	public String getAll(@PathVariable final String shopPath,
			@PathVariable final Integer page, final Model model) {
		Long shopId = shopService.getShopByPath(shopPath).getId();
		Page<Address> addresses = service.getAddressesByShopId(page, shopId);
		model.addAttribute("addresses", addresses);
		return "addresses";
	}

	@RequestMapping(path = "/id/{clientId}/address", method = RequestMethod.GET)
	public @ResponseBody List<Address> getByUser(
			@PathVariable final Long clientId) {
		List<Address> addresses = service.getAddressesByClientId(clientId);
		return addresses;
	}

	@RequestMapping(path = "/user_{clientId}/add", method = RequestMethod.POST)
	public String add(@PathVariable final Long clientId,
			@ModelAttribute("addr") final Address addr) {
		addr.setClient(clientService.getClientById(clientId));
		service.createAddress(addr);
		return "redirect:/user_{clientId}";
	}

	@RequestMapping(path = "/user_{clientId}/{addressId}", method = RequestMethod.POST)
	public String edit(@ModelAttribute("addr") final Address addr) {
		service.updateAddress(addr);
		return "redirect:/user_{clientId}";
	}
}
