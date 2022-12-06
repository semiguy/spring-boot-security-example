package com.laurentiuspilca.ssia.filters;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.web.csrf.CsrfToken;

public class CsrfTokenLogger implements Filter {
	
	private Logger logger = Logger.getLogger(CsrfTokenLogger.class.getName());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		Object o = request.getAttribute("_csrf");
		CsrfToken token = (CsrfToken) o; // _csrf 요청 특성에서 토큰의 값을 얻고 콘솔에 출력
		
		logger.info("CSRF token " + token.getToken()); 
		
		filterChain.doFilter(request, response); 
	}
	
	
}
