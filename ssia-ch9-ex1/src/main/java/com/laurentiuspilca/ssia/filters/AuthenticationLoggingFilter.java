package com.laurentiuspilca.ssia.filters;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class AuthenticationLoggingFilter implements Filter {
	
	private final Logger logger = Logger.getLogger(AuthenticationLoggingFilter.class.getName());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		String requestId = httpRequest.getHeader("Request-Id"); // 요청 헤더에서 요청 ID를 얻음
		
		logger.info("Successfully authenticated request with id " + requestId); // 요청 ID의 값과 이벤트 기록	
		
		filterChain.doFilter(request, response); // 요청을 필터 체인의 다음 필터에 전달
		
	}
	
	
}
