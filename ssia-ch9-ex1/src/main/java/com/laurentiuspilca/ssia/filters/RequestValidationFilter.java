package com.laurentiuspilca.ssia.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 맞춤형 필터 구현
 */
public class RequestValidationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String requestId = httpRequest.getHeader("Request-Id");
		
		if(requestId == null || requestId.isEmpty()) {
			
			// 헤더가 없으면 HTTP 상태가 '400 잘못된 요청'으로 바뀌고 
			// 요청이 필터 체인의 다음 필터로 전달되지 않는다.
			httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST); 
			
			return;
		}
		
		// 헤더가 있으면 요청을 필터 체인의 다음 필터로 전달한다.
		filterChain.doFilter(request, response); 
	}
	
	
}
