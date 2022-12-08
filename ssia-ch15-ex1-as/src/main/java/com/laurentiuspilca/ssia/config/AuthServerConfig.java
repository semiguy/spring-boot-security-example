package com.laurentiuspilca.ssia.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Value("${jwt.key}")
	private String jwtKey; // 대칭 키의 값을 얻음
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
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
		// 토큰 저장소와 액세스 토큰 컨버터 객체 구성
		endpoints
			.authenticationManager(authenticationManager)
			.tokenStore(tokenStore())
			.accessTokenConverter(jwtAccessTokenConverter());
			
	}
	
	@Bean
	public TokenStore tokenStore() {
		
		// 액세스 토큰 컨버터를 연결하고 토큰 저장소 생성
		return new JwtTokenStore(jwtAccessTokenConverter());
	}
	
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		// 액세스 토큰 컨버터를 위해 대칭 키의 값 설정
		converter.setSigningKey(jwtKey);
		
		return converter;
	}
}
