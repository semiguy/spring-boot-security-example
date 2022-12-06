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
	public UserDetailsService uds() {
		
		UserDetailsManager uds = new InMemoryUserDetailsManager();
		
		UserDetails u1 = User.withUsername("mary")
				.password("12345")
				.authorities("READ")
				.build();
		
		uds.createUser(u1);
		
		return uds;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return NoOpPasswordEncoder.getInstance();
	}
	
	// 양식 로그인 인증 방식을 설정하고 인증된 사용자만
	// 엔드포인트에 접근할 수 있게 configure()를 재정의 한다.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//super.configure(http);
		http
			.authorizeRequests()
				.anyRequest().authenticated();
		
		http
			.formLogin()
				.defaultSuccessUrl("/main", true);

	}
}
