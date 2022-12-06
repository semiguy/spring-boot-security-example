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
				.authorities("READ")
				.build();
		
		UserDetails user2 = User.withUsername("jane")
				.password("12345")
				.authorities("WRITE")
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
		
		//http.authorizeRequests().anyRequest().permitAll(); // 모든 요청에 대해 엑세스를 허용.
		//http.authorizeRequests().anyRequest().hasAuthority("WRITE"); // 사용자가 엔드포인트에 접근하기 위한 조건 지정
		//http.authorizeRequests().anyRequest().hasAnyAuthority("WRITE", "READ"); // WRITE 및 READ 권한이 있는 사용자의 요청을 모두 허용
		http.authorizeRequests().anyRequest().access("hasAuthority('WRITE')"); // WRITE 권한이 있는 사용자의 요청에 권한 부여
		
	}
}
