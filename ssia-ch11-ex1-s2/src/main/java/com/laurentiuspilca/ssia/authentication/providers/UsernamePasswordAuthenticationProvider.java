package com.laurentiuspilca.ssia.authentication.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.laurentiuspilca.ssia.authentication.UsernamePasswordAuthentication;
import com.laurentiuspilca.ssia.authentication.proxy.AuthenticationServerProxy;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private AuthenticationServerProxy proxy;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = authentication.getName();
		String password = String.valueOf(authentication.getCredentials());
		
		// 프락시 인증 서버를 호출. SMS를 통해 클라이언트에 OTP를 보낸다.
		proxy.sendAuth(username, password); 
		
		return new UsernamePasswordAuthenticationToken(username, password);
		//return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		// Authentication의 UsernamePasswordAuthentication 형식을 지원할 AuthenticationProvider를 설계
		return UsernamePasswordAuthentication.class.isAssignableFrom(authentication);
		//return false;
	}
	
	
}
