package com.laurentiuspilca.ssia.filters;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;


/*
 * 9.9 OncePerRequestFilter 클래스 확장
 */

public class AuthenticationLoggingFilter extends OncePerRequestFilter {
	
	private final Logger logger = Logger.getLogger(AuthenticationLoggingFilter.class.getName());
	
	/*
	 * Filter 인터페이스의 doFilter() 메서드의 용도를 대체하는 doFilterInternal() 재정의
	 * OncePerRequestFilter는 HTTP 필터만 지원한다.
	 * 이 때문에 HttpServletRequest 및 HttpServletResponse로 매개 변수를 직접 지정했다. 
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String requestId = request.getHeader("Request-Id");
		
		logger.info("Successfully authenticated request with id " + requestId);
		
		filterChain.doFilter(request, response); 
	}

}
