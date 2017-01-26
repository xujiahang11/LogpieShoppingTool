package com.logpie.shopping.tool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.logpie.shopping.tool.service.BrandService;

@Controller
public class HomePageController {
	@Autowired
	BrandService service;

	@RequestMapping(path = "/greeting/{name}", method = RequestMethod.GET)
	public String greeting(@PathVariable String name, Model model) {
		service.createBrand(name);
		model.addAttribute("name", name);
		return "greeting";
	}

}
