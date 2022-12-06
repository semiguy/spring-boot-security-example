package com.laurentiuspilca.ssia.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 
		UserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
		
		// 새 사용자 생성
		UserDetails user = User.withUsername("john")
				.password("12345")
				.authorities("read")
				.build();
		
		// 사용자 추가
		userDetailsService.createUser(user);
		
		// UserDetailsService 및 PasswordEncoder 구성
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(NoOpPasswordEncoder.getInstance());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//super.configure(http);
		http.httpBasic();
		
		http.authorizeHttpRequests()
			.anyRequest().authenticated(); // 모든 요청에 인증을 요구하도록 설정
	}
	
	
}
