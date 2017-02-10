package com.logpie.shopping.tool;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.logpie.framework.log.util.LogColor;
import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;

@Component
public class LogpieInterceptor extends HandlerInterceptorAdapter {
	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(LogpieInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) {
		logger.trace("Interceptor starts to prehandle...");

		RequestContextHolder.getRequestAttributes().setAttribute("requestId",
				UUID.randomUUID(), RequestAttributes.SCOPE_REQUEST);
		String requestMsg = "Request id --- ".toUpperCase();
		logger.info(requestMsg
				+ LogColor.setPurple(RequestContextHolder
						.getRequestAttributes()
						.getAttribute("requestId",
								RequestAttributes.SCOPE_REQUEST).toString()));

		long startTime = System.currentTimeMillis();
		RequestContextHolder.getRequestAttributes().setAttribute(
				"requestStartTime", startTime, RequestAttributes.SCOPE_REQUEST);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
		logger.trace("Interceptor starts to posthandle...");

		String responseMsg = "Reponses id --- ".toUpperCase();
		logger.info(responseMsg + LogColor.setPurple("2416-9381-2093-9800"));
		// TODO

		long endTime = System.currentTimeMillis();
		long executeTime = endTime
				- (long) RequestContextHolder.getRequestAttributes()
						.getAttribute("requestStartTime",
								RequestAttributes.SCOPE_REQUEST);
		String performanceMsg = "Request execution time --- ".toUpperCase();

		logger.info(performanceMsg
				+ LogColor.setPurple(String.valueOf(executeTime) + "ms"));

		LogpieLoggerFactory.output();

	}
}
