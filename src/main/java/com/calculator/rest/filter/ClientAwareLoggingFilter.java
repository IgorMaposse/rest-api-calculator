package com.calculator.rest.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.MDC;
import org.springframework.stereotype.Component;

@Component
public class ClientAwareLoggingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		String uniqueId = UUID.randomUUID().toString();
		
		HttpServletResponse httpResponse = (HttpServletResponse) res;
		httpResponse.setHeader("UNIQUE_ID", uniqueId);
		
		try {
			MDC.put("UNIQUE_ID", uniqueId);
			
			chain.doFilter(req, res);
		}
		finally {
			MDC.remove("UNIQUE_ID");
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}	
	
	@Override
	public void destroy() {}	
	
}
