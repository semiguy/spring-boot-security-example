package com.laurentiuspilca.ssia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.laurentiuspilca.ssia.model.CustomUserDetails;

/*
 * 맞춤형 인증 논리 구현.
 */

@Service
public class AuthenticationProviderService implements AuthenticationProvider {
	
	// UserDetailsService와 PasswordEncoder 필요
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private SCryptPasswordEncoder sCryptPasswordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		//return null;
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		// userDetailsServicefh DB 사용자 세부 정보 검색
		CustomUserDetails user = userDetailsService.loadUserByUsername(username);
		
		// 해당 사용자에 맞는 해싱 알고리즘으로 암호 검증
		switch (user.getUser().getAlgorithm()) {
		case BCRYPT:
			return checkPassword(user, password, bCryptPasswordEncoder);
		case SCRYPT:
			return checkPassword(user, password, sCryptPasswordEncoder);
		}
		
		throw new BadCredentialsException("Bad credentials");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		//return false;
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	// 암호 인코더 등록
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SCryptPasswordEncoder sCryptPasswordEncoder() {
		
		return new SCryptPasswordEncoder();
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	private Authentication checkPassword(CustomUserDetails user, String rawPassword, PasswordEncoder encoder) {
		
		if(encoder.matches(rawPassword, user.getPassword())) {
			
			return new UsernamePasswordAuthenticationToken(
					user.getUsername(), user.getPassword(), user.getAuthorities());
		} else {
			
			throw new BadCredentialsException("Bad credentials");
		}
	}
	
}
