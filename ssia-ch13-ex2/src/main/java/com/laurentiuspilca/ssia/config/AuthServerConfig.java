package com.laurentiuspilca.ssia.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	// 승인 코드 그랜트 유형 설정
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		//super.configure(clients);
		clients.inMemory()
				.withClient("client")
				.secret("secret")
				.authorizedGrantTypes("authorization_code")
				.scopes("read")
				.redirectUris("http://localhost:9090/home");
	}
	
	// 다른 그랜트 유형의 클라이언트 구성
//	@Override
//	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		
//		//super.configure(clients);
//		clients.inMemory()
//				.withClient("client1")
//				.secret("secret1")
//				// ID가 client1인 클라이언트는 승인 코드 그랜트만 이용
//				.authorizedGrantTypes("authorization_code")	
//				.scopes("read")
//				.redirectUris("http://localhost:9090/home")
//				.and()
//				.withClient("client2")
//				.secret("secret2")
//				// ID가 client1인 클라이언트는 승인 코드, 암호, 갱신 토큰 허가를 모두 이용
//				.authorizedGrantTypes("authorization_code", "password", "refresh_token") 
//				.scopes("read")
//				.redirectUris("http://localhost:9090/home");
//	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		//super.configure(endpoints);
		endpoints.authenticationManager(authenticationManager);
	}
}
