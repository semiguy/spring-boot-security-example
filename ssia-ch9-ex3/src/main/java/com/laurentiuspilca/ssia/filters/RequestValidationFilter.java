package com.laurentiuspilca.ssia.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestValidationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String requestId = httpRequest.getHeader("Request-Id");
		
		if(requestId == null || requestId.isEmpty()) {
			
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		filterChain.doFilter(request, response); 
	}
	
	
}
