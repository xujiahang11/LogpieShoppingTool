package com.logpie.shopping.tool;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.AbstractUrlHandlerMapping;

@Component
public class LogpieHandlerMapping extends AbstractUrlHandlerMapping {

	@Override
	public Object lookupHandler(String urlPath, HttpServletRequest request)
			throws Exception {
		return super.lookupHandler(urlPath, request);
	}
}
