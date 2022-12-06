package com.laurentiuspilca.ssia.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.laurentiuspilca.ssia.authentication.filters.InitialAuthenticationFilter;
import com.laurentiuspilca.ssia.authentication.filters.JwtAuthenticationFilter;
import com.laurentiuspilca.ssia.authentication.providers.OtpAuthenticationProvider;
import com.laurentiuspilca.ssia.authentication.providers.UsernamePasswordAuthenticationProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private InitialAuthenticationFilter initialAuthenticationFilter;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Autowired
	private OtpAuthenticationProvider otpAuthenticationProvider;
	
	@Autowired
	private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//super.configure(auth);
		auth.authenticationProvider(otpAuthenticationProvider)
			.authenticationProvider(usernamePasswordAuthenticationProvider);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//super.configure(http);
		http.csrf().disable(); // CSRF 비활성화
		
		// 두 맞춤형 필터를 필터 체인에 추가
		http.addFilterAt(initialAuthenticationFilter, BasicAuthenticationFilter.class)
			.addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class);
		
		// 모든 요청이 인증되게 한다.
		http.authorizeRequests()
				.anyRequest().authenticated();
	}
	
	@Override
	@Bean // 필터 클래스에서 자동 주입할 수 있게 AuthenticationManager를 스프링 컨텍스트에 추가.
	protected AuthenticationManager authenticationManager() throws Exception {
		
		return super.authenticationManager();
	}
}
