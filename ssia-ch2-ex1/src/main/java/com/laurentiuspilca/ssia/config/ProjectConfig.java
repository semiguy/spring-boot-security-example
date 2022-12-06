package com.laurentiuspilca.ssia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
		
		// 주어진 사용자 이름, 암호, 권한 목록으로 사용자 생성
		UserDetails user = User.withUsername("john")
				.password("12345")
				.authorities("read")
				.build();
		
		// UserDetailsService에서 관리하도록 사용자 추가
		userDetailsService.createUser(user);
				
		return userDetailsService;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {

		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//super.configure(http);
		http.httpBasic();
		
		http.authorizeRequests()
			//.anyRequest().authenticated(); // 모든 요청에 필요하다.
			.anyRequest().permitAll();	// 인증 없이 요청할 수 있다.
	}
	
	
}
