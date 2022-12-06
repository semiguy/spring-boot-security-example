package com.laurentiuspilca.ssia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;

import com.laurentiuspilca.ssia.csrf.CustomCsrfTokenRepository;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
	
	@Bean // CsrfTokenRepository를 컨텍스트에 빈으로 정의
	public CsrfTokenRepository customTokenRepository() {
		
		return new CustomCsrfTokenRepository();
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//super.configure(http);
		// 람다 식의 매개 변수는 CsrfConfigure다. 
		// 메서드를 호출해 다양한 방식으로 CSRF 보호를 구성할 수 있다.
		http
			.csrf(c -> {
				// Customizer<CsrfConfigure<HttpSecurity>> 객체를 이용해 
				// 새 CsrfTokenRepository 구현을 CSRF 보호 매커니즘에 연결
				c.csrfTokenRepository(customTokenRepository());
				c.ignoringAntMatchers("/ciao");
			});
		
		http.authorizeRequests()
			.anyRequest().permitAll();
	}
}
