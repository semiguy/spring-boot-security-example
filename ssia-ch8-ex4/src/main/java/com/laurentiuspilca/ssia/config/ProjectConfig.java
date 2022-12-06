package com.laurentiuspilca.ssia.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//super.configure(http);
		http.httpBasic();
		
		// 정규식이 포함된 매개 변수 식을 이용할 때는 
		// 매개 변수, 콜론(:), 정규식 사이에 공백이 없어야 한다.
		http.authorizeRequests()
			.mvcMatchers("/product/{code:^[0-9]*$}") // 길이와 관계없이 숫자를 포함하는 문자열을 나타내는 정규식
				.permitAll()
			.anyRequest()
				.denyAll();
	}
}
