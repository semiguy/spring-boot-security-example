package com.laurentiuspilca.ssia.config;

import java.util.Arrays;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//super.configure(http);
		http.cors(c -> {
			CorsConfigurationSource source = request -> {
				CorsConfiguration config = new CorsConfiguration();
				//config.setAllowedOrigins(Arrays.asList("example.com", "example.org"));
				//config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
				config.setAllowedOrigins(Arrays.asList("*"));
				config.setAllowedMethods(Arrays.asList("*"));
				
				return config;
			};
			c.configurationSource(source);
		});
		
		http.csrf().disable();
		
		http.authorizeRequests().anyRequest().permitAll();
	}
}
