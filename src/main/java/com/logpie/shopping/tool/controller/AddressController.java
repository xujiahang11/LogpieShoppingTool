package com.logpie.shopping.tool.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@RequestMapping(path = "/id/{clientId}/address", method = RequestMethod.GET)
	public @ResponseBody List<Address> getListByAJAX(
			@PathVariable final BigInteger clientId) {
		List<Address> addresses = service.getAddressesByClientId(clientId);
		return addresses;
	}

	@RequestMapping(path = "/id/{clientId}/address/{id}", method = RequestMethod.GET)
	public @ResponseBody Address getByAJAX(@PathVariable final BigInteger id) {
		return service.getAddressById(id);
	}

	@RequestMapping(path = "/id/{clientId}/address/create", method = RequestMethod.POST)
	public @ResponseBody String createByAJAX(@PathVariable final BigInteger clientId,
			@RequestBody final Address addr) {
		addr.setClient(clientService.getClientById(clientId));
		service.createAddress(addr);
		return "success";
	}

	@RequestMapping(path = "/id/{clientId}/address/edit", method = RequestMethod.POST)
	public @ResponseBody String editByAJAX(@RequestBody final Address addr) {
		service.updateAddress(addr);
		return "success";
	}
}
