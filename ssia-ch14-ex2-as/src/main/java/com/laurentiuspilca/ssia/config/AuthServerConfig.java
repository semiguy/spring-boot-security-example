package com.laurentiuspilca.ssia.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	// application.properties 파일에서 구성한 데이터 원본 주입
	@Autowired
	private DataSource dataSource;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		//super.configure(clients);
		clients.inMemory()
				.withClient("client")
				.secret("secret")
				.authorizedGrantTypes("password", "refresh_token")
				.scopes("read");
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		//super.configure(endpoints);
		endpoints
			.authenticationManager(authenticationManager)
			// 토큰 저장소 구성
			.tokenStore(tokenStore());
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	@Bean
	public TokenStore tokenStore() {
		
		// application.properties 파일에 구성된 데이터 원본을 통해
		// 데이터베이스에 대한 접근을 제공하는 JdbcTokenStore의 인스턴스 생성
		return new JdbcTokenStore(dataSource);
	}
}
