package com.laurentiuspilca.ssia.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.laurentiuspilca.ssia.filters.StaticKeyAuthenticationFilter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired // 스프링 컨텍스트에서 필터의 인스턴스를 주입
	private StaticKeyAuthenticationFilter filter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//super.configure(http);
		http.addFilterAt(filter, BasicAuthenticationFilter.class) // 필터 체인에서 기본 인증 필터의 위치에 필터 추가
			.authorizeRequests()
				.anyRequest().permitAll();
	}
	
	
}
