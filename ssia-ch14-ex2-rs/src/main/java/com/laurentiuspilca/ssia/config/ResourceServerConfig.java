package com.laurentiuspilca.ssia.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		
		//super.configure(resources);
		// 토큰 저장소 구성
		resources.tokenStore(tokenStore());
	}
	
	@Bean
	public TokenStore tokenStore() {
		// 주입된 데이터 원본을 바탕으로 JdbcTokenStore 생성
		return new JdbcTokenStore(dataSource);
	}
}
