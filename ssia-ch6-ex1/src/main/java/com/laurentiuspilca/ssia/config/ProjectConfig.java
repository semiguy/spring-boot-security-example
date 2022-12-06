package com.laurentiuspilca.ssia.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import com.laurentiuspilca.ssia.services.AuthenticationProviderService;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
	
	// 컨텍스트에서 AuthenticationProviderService의 인스턴스를 얻음
	@Autowired
	private AuthenticationProviderService authenticationProvider;
	
	// 암호 인코더 등록
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SCryptPasswordEncoder sCryptPasswordEncoder() {
		
		return new SCryptPasswordEncoder();
	}

	// configure() 메서드를 재정의해서 스프링 시큐리티를 위한 인증 공급자를 등록
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//super.configure(auth);
		auth.authenticationProvider(authenticationProvider);
	}
	
	// formLogin을 인증 메서드로 구성
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//super.configure(http);
		http.formLogin()
			.defaultSuccessUrl("/main", true);
		
		http.authorizeHttpRequests()
			.anyRequest().authenticated();
	}
	
}
