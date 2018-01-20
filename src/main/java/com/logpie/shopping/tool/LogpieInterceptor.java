package com.logpie.shopping.tool;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logpie.framework.log.util.LogpieLoggerWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.logpie.framework.log.util.LogpieLogger;
import com.logpie.framework.log.util.LogpieLoggerFactory;

@Component
public class LogpieInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private ApplicationContext appContext;

	private LogpieLogger LOG = LogpieLoggerFactory
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
		// set up requestId
        UUID requestId = UUID.randomUUID();
		RequestContextHolder.getRequestAttributes().setAttribute("requestId",
				requestId, RequestAttributes.SCOPE_REQUEST);

		// set up request start time for matrix
		long startTime = System.currentTimeMillis();
		RequestContextHolder.getRequestAttributes().setAttribute(
				"requestStartTime", startTime, RequestAttributes.SCOPE_REQUEST);

        String ip = getIpUtil(request);
		RequestContextHolder.getRequestAttributes().setAttribute("ip", ip, RequestAttributes.SCOPE_REQUEST);

        LOG.info("REQUEST ID --- " + requestId.toString());
        LOG.info("IP ADDRESS --- " + ip);
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

	}

	/*
	 * 整个请求处理完毕回调方法（即视图渲染完毕时调用）
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
        // calculate request execution time for matrix
        long endTime = System.currentTimeMillis();
        long requestExecuteTime = endTime
                - (long) RequestContextHolder.getRequestAttributes()
                .getAttribute("requestStartTime", RequestAttributes.SCOPE_REQUEST);

        LOG.info("REQUEST EXECUTION TIME --- " + requestExecuteTime + "ms");

        // output logs
        try {
            LogpieLoggerFactory.logToFile(false);
        } catch (IOException e) {
            LOG.error("Failed to append log to --- " + LogpieLoggerWriter.getDefaultPath() + LogpieLoggerWriter.getFileName());
            e.printStackTrace();
        }
        LogpieLoggerFactory.logToConsole(true);
    }

    private String getIpUtil(HttpServletRequest request) {
        String ip = request.getHeader("X-FORWARDED-FOR");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
