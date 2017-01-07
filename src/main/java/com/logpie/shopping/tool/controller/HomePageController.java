package com.logpie.shopping.tool.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageController {

	@RequestMapping("/home")
	public String showHomePage(@RequestParam(value = "name") String name) {
		return "Hello, " + name;
	}
}
