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
		
		UserDetailsManager manager = new InMemoryUserDetailsManager();
		
		UserDetails user1 = User.withUsername("john")
				.password("12345")
				//.authorities("ROLE_ADMIN") // ROLE_ 접두가사 있으므로 GrantedAuthority는 역할을 나타낸다.
				.roles("ADMIN") // roles() 메서드로 사용자의 역할을 지정한다.
				.build();
		
		UserDetails user2 = User.withUsername("jane")
				.password("12345")
				//.authorities("ROLE_MANAGER")
				.roles("MANAGER")
				.build();
		
		// 사용자는 UserDetailsService에 의해 추가되고 관리
		manager.createUser(user1);
		manager.createUser(user2);
		
		return manager;
	}
	
	@Bean
	public PasswordEncoder PasswordEncoder() {
		
		return NoOpPasswordEncoder.getInstance();
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//super.configure(http);
		http.httpBasic();
		
		// 관리자의 요청만 수락하도록 앱 구성
		// 역할을 선언할 때만 ROLE_ 접두사를 쓴다.
		http.authorizeHttpRequests().anyRequest().hasRole("ADMIN");
	}
}
