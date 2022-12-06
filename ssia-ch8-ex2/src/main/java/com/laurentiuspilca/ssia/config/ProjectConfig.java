package com.laurentiuspilca.ssia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
		
		// 사용자를 저장하는 InMemoryUserDetailsManager
		UserDetailsManager manager = new InMemoryUserDetailsManager();
		
		UserDetails user1 = User.withUsername("john")
				.password("12345")
				.roles("ADMIN") // 사용자 존은 관리자 역할을 가짐
				.build();
		
		UserDetails user2 = User.withUsername("jane")
				.password("12345")
				.roles("MANAGER")	// 사용자 제인은 운영자 역할을 가짐
				.build();
		
		manager.createUser(user1);
		manager.createUser(user2); 
		
		return manager;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return NoOpPasswordEncoder.getInstance();	// PasswordEncoder도 추가해야 한다.
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//super.configure(http);
		http.httpBasic();
		
		http.authorizeRequests()
			.mvcMatchers(HttpMethod.GET, "/a")
				.authenticated()	// HTTP GET 방식으로 /a 경로를 요청하면 앱이 사용자를 인증해야 한다.
			.mvcMatchers(HttpMethod.POST, "/a")
				.permitAll()		// HTTP POST 방식으로 /a 경로를 요청하면 모두 허용
			.anyRequest()
				.denyAll();			// 다른 경로에 대한 모든 요청 거부
		
		http.csrf().disable();	// HTTP POST 방식으로 /a 경로를 호출할 수 있게 CSRF 비활성화
	}
}
