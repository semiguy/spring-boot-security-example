package com.laurentiuspilca.ssia.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		//super.configure(clients);
		clients.inMemory()
				.withClient("client")
				.secret("secret")
				.authorizedGrantTypes("password", "refresh_token")
				.scopes("read")
			// 리소스 서버가 /oauth/check_token 엔드포인트를 호출할 때 이용할 자격 증명 추가
			.and()
				.withClient("resourceserver")
				.secret("resourceserversecret");
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		//super.configure(endpoints);
		endpoints.authenticationManager(authenticationManager);
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		
		// check_token 엔드포인트를 호출할 수 있는 조건 지정
		//super.configure(security);
		security.checkTokenAccess("isAuthenticated()");
	}
}
