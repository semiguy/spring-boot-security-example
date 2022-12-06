package com.laurentiuspilca.ssia.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.laurentiuspilca.ssia.filters.AuthenticationLoggingFilter;
import com.laurentiuspilca.ssia.filters.RequestValidationFilter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//super.configure(http);
		http
			.addFilterBefore( // 필터 체인에서 인증 필터 앞에 맞춤형 필터의 인스턴스 추가
					new RequestValidationFilter(), 
					BasicAuthenticationFilter.class)
			.addFilterAfter( // 필터 체인에서 인증 필터 다음에 AuthenticationLoggingFilter의 인스턴스 추가
					new AuthenticationLoggingFilter(), 
					BasicAuthenticationFilter.class)
			.authorizeRequests()
				.anyRequest().permitAll();
		
	}
}
