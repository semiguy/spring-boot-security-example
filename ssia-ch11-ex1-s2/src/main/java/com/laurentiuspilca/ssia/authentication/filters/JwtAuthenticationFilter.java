package com.laurentiuspilca.ssia.authentication.filters;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.laurentiuspilca.ssia.authentication.UsernamePasswordAuthentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Value("${jwt.signing.key}")
    private String signingKey;
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		
		//return super.shouldNotFilter(request);
		// /login 경로에 대한 요청에는 트리거되지 않도록 구성
		return request.getServletPath().equals("/login");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwt = request.getHeader("Authorization");
		
		SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
		
		// 토큰을 구문 분석해 클레임을 얻고 서명을 검증
		Claims claims = Jwts.parserBuilder()
							.setSigningKey(key)
							.build()
							.parseClaimsJws(jwt)
							.getBody();
		
		String username = String.valueOf(claims.get("username"));
		
		// SecurityContext에 추가할 Authentication 인스턴스를 만든다.
		GrantedAuthority a = new SimpleGrantedAuthority("user");
		Authentication auth = new UsernamePasswordAuthentication(username, null, Arrays.asList(a));
		
		// SecurityContext에 Authenticatin 객체를 추가
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		// 필터 체인의 다음 필터를 호출
		filterChain.doFilter(request, response); 
	}

	
	
}
