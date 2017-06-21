package com.logpie.shopping.tool;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
	@Autowired
	private ApplicationContext appContext;

	private LogpieLogger logger = LogpieLoggerFactory
			.getLogger(LogpieInterceptor.class);

	/*
	 * 预处理回调方法
	 * 
	 * 若方法返回值为true，请求继续（调用下一个拦截器或处理器方法）
	 * 若方法返回值为false，请求处理流程中断，不会继续调用其他的拦截器或处理器方法，此时需要通过response产生响应
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.trace("Interceptor starts to prehandle...");

		// set up requestId
		RequestContextHolder.getRequestAttributes().setAttribute("requestId",
				UUID.randomUUID(), RequestAttributes.SCOPE_REQUEST);
		String requestMsg = "Request id --- ".toUpperCase();
		logger.info(requestMsg
				+ LogColor.setPurple(RequestContextHolder
						.getRequestAttributes()
						.getAttribute("requestId",
								RequestAttributes.SCOPE_REQUEST).toString()));

		// set up request start time for matrix
		long startTime = System.currentTimeMillis();
		RequestContextHolder.getRequestAttributes().setAttribute(
				"requestStartTime", startTime, RequestAttributes.SCOPE_REQUEST);

		logger.trace("Interceptor is done with prehandle...");
		return true;
	}

	/*
	 * 后处理回调方法
	 * 
	 * 实现处理器的后处理（但在渲染视图之前），此时可以通过modelAndView对模型数据进行处理或对视图进行处理
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
		logger.trace("Interceptor starts to posthandle...");

		// TODO set up response id
		// String responseMsg = "Responses id --- ".toUpperCase();
		// logger.info(responseMsg + LogColor.setPurple("2416-9381-2093-9800"));

		// calculate request execution time for matrix
		long endTime = System.currentTimeMillis();
		long executeTime = endTime
				- (long) RequestContextHolder.getRequestAttributes()
						.getAttribute("requestStartTime",
								RequestAttributes.SCOPE_REQUEST);
		String performanceMsg = "Request execution time --- ".toUpperCase();
		logger.info(performanceMsg
				+ LogColor.setPurple(String.valueOf(executeTime) + "ms"));
		logger.trace("Interceptor is done with posthandle...");

		// output logs
		LogpieLoggerFactory.outputLog();
	}

	/*
	 * 整个请求处理完毕回调方法（即视图渲染完毕时调用）
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {

	}
}
