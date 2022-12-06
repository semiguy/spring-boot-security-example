package com.laurentiuspilca.ssia.security;

import java.util.Arrays;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/*
 * 인증 공급자
 */

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		// 인증 논리를 추가할 위치
		//return null;
		// Principal 인터페이스의 getName() 메서드를 Authentication에서 상속
		String username = authentication.getName();
		String password = String.valueOf(authentication.getCredentials());
		
		// 일반적으로 UserDetailsService 및 PasswordEncoder를 
		// 호출해서 사용자 이름과 암호를 테스트
		if("john".equals(username) && "12345".equals(password)) {
			
			return new UsernamePasswordAuthenticationToken(username, password, Arrays.asList()); 
		} else {
			
			throw new AuthenticationCredentialsNotFoundException("Error is authentication!");
		}
	}

	@Override
	public boolean supports(Class<?> authenticationType) {
		
		// Authentication 형식의 구현을 추가할 위치
		//return false;
		return UsernamePasswordAuthenticationToken.class
				.isAssignableFrom(authenticationType);
	}
	
	
}
