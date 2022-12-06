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
		http.addFilterBefore(
				new RequestValidationFilter(), 
				BasicAuthenticationFilter.class)
			.addFilterAfter(
				new AuthenticationLoggingFilter(), 
				BasicAuthenticationFilter.class)
			.authorizeRequests()
				.anyRequest()
					.permitAll();
	}
	
	
}
