package com.laurentiuspilca.ssia.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
	
	// 컨텍스트에서 AuthenticationManager 인스턴스를 주입
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		//super.configure(clients);
		clients.inMemory()
				.withClient("client")
				.secret("secret")
				.authorizedGrantTypes("password")
				.scopes("read");
	}
	
//	// ClientDetailsService 인스턴스 설정
//	@Override
//	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		
//		//super.configure(clients);
//		// ClientDetailsService 구현현을 이용해 인스턴스 생성
//		InMemoryClientDetailsService service = new InMemoryClientDetailsService();
//		
//		// ClientDetails의 인스턴스를 만들고 클라이언트에 관한 필수 세부 정보 설정
//		BaseClientDetails cd = new BaseClientDetails();
//		cd.setClientId("client");
//		cd.setClientSecret("secret");
//		cd.setScope(Arrays.asList("read"));
//		cd.setAuthorizedGrantTypes(Arrays.asList("password"));
//		
//		
//		Map<String, BaseClientDetails> store = new HashMap<>();
//		store.put("client", cd);
//		// InMemoryClientDetailsService에 ClientDetails 인스턴스 추가
//		service.setClientDetailsStore(store);
//		
//		// 권한 부여 서버에서 이용할 수 있게 ClientDetailsService 구성
//		clients.withClientDetails(service);
//	
//	}
	
	// AuthenticationManager를 설정하도록 configure() 메서드를 재 정의
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		//super.configure(endpoints);
		endpoints.authenticationManager(authenticationManager);
	}
}
