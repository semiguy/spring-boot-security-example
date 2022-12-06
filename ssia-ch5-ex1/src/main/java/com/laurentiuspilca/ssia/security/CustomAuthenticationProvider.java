package com.laurentiuspilca.ssia.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 인증 논리
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		// return null;
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		UserDetails u = userDetailsService.loadUserByUsername(username);
		
		if(passwordEncoder.matches(password, u.getPassword())) {
			
			// 암호가 일치하면 필요한 세부 정보가 포함된 Authentication 계약의 구현을 반환
			return new UsernamePasswordAuthenticationToken(username, password, u.getAuthorities());
		} else {
			
			// 암호가 일치하지 않으면 AuthenticationException 형식의 예외를 투척한다.
			// BadCredentialsException은 AuthenticationException을 상속한다.
			throw new BadCredentialsException("Something went wrong!");
		}
	}

	@Override
	public boolean supports(Class<?> authenticationType) {
		
		//return false;
		return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	
	
}
