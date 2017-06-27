package com.logpie.shopping.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logpie.shopping.tool.model.Brand;
import com.logpie.shopping.tool.model.Shop;
import com.logpie.shopping.tool.service.BrandService;
import com.logpie.shopping.tool.service.ShopService;

@Controller
@RequestMapping("/{shopPath}/products")
public class BrandController {

	@Autowired
	private BrandService service;
	@Autowired
	private ShopService shopService;

	@RequestMapping(path = "/brand/create", method = RequestMethod.POST)
	public @ResponseBody String createByAJAX(
			@PathVariable final String shopPath, @RequestBody final Brand brand) {
		Shop shop = shopService.getShopByPath(shopPath);
		brand.setShop(shop);
		service.createBrand(brand);

		return brand.getName();
	}
}
