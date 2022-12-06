package com.laurentiuspilca.ssia.authentication.filters;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.laurentiuspilca.ssia.authentication.OtpAuthentication;
import com.laurentiuspilca.ssia.authentication.UsernamePasswordAuthentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class InitialAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private AuthenticationManager manager;
	
	@Value("${jwt.signing.key}")
	private String signingKey;
	
	// 요청에 따라 올바른 인증을 요구하도록 doFilterInternal() 재정의
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String username = request.getHeader("username");
		String password = request.getHeader("password");
		String code = request.getHeader("code");
		
		if(code == null) {
			
			Authentication a = new UsernamePasswordAuthentication(username, password);
			manager.authenticate(a);
		} else {
			
			Authentication a = new OtpAuthentication(username, code);
			a = manager.authenticate(a);
			
			SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
			
			Map<String, String> claims = new HashMap<>();
			claims.put("username", username);
			
			String jwt = Jwts.builder()
					.setClaims(claims)
					.signWith(key)
					.compact();
			
			// 토큰을 HTTP 응답의 권한 부여 헤더에 추가.
			response.setHeader("Authorization", jwt);
		}
		
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		
		//return super.shouldNotFilter(request);
		return !request.getServletPath().equals("/login"); // /login 경로에만 이 필터를 적용
	}
	
	
}
