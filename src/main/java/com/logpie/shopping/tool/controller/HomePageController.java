package com.logpie.shopping.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.logpie.shopping.tool.model.Brand;
import com.logpie.shopping.tool.service.BrandService;

@Controller
public class HomePageController {
	@Autowired
	BrandService service;

	@RequestMapping("/greeting")
	public String greeting(
			@RequestParam(value = "id", required = false, defaultValue = "World") String id,
			Model model) {
		Brand brand = service.getBrandById(id);
		model.addAttribute("name", brand.getmBrandName());
		return "greeting";
	}

}
