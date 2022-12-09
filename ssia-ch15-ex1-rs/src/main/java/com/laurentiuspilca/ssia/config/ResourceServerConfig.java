package com.laurentiuspilca.ssia.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Value("${jwt.key}")
	private String jwtKey;
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		
		//super.configure(resources);
		// TokenStore 구성
		resources.tokenStore(tokenStore());
	}
	
	@Bean
	public TokenStore tokenStore() {
		// TokenStore를 선언하고 스프링 컨텍스트에 추가
		return new JwtTokenStore(jwtAccessTokenConverter());
	}
	
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		// 액세스 토큰 컨버터를 만들고 토큰 서명을 검증하는데 이용할 대칭 키 설정
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(jwtKey);
		
		return converter;
	}
}
