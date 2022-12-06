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
				.authorities("read")
				.build();
		
		UserDetails user2 = User.withUsername("jane")
				.password("12345")
				.authorities("read", "write", "delete")
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
		
		// 사용자에게 읽기 권한이 있어야 하지만 삭제 권한은 없어야 함을 나타낸다.
		String expression = "hasAuthority('read') and !hasAuthority('delete')";
		http.authorizeRequests().anyRequest().access(expression);
	}
	
	
	
}
