package com.laurentiuspilca.ssia.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component // 속성 파일에서 값을 주입할 수 있도록 스프링 컨텍스트에 클래스의 인스턴수 추가
public class StaticKeyAuthenticationFilter implements Filter {
	
	// @Value 어노테이션으로 속성 파일에서 정적 키의 값을 얻음
	@Value("${authorization.key}")
	private String authorizationKey;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		// 요청의 Authorization 헤더에서 값을 얻고 정적 헤더와 비교
		String authentication = httpRequest.getHeader("Authorization");
		
		if(authorizationKey.equals(authentication)) {
			
			filterChain.doFilter(request, response);
		} else {
			
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); 
		}
	}
	
	
	
}
