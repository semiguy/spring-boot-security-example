package com.laurentiuspilca.ssia.authentication.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.laurentiuspilca.ssia.authentication.model.User;

@Component
public class AuthenticationServerProxy {
	
	@Autowired
	private RestTemplate rest;
	
	@Value("${auth.server.base.url}")
	private String baseUrl;
	
	public void sendAuth(String username, String password) {
		
		String url = baseUrl + "/user/auth";
		
		// 이 호출에는 HTTP 요청 본문에 사용자 이름과 암호가 필요하다.
		User body = new User();
		body.setUsername(username);
		body.setPassword(password);
		
		HttpEntity request = new HttpEntity<>(body);
		
		rest.postForEntity(url, request, Void.class);
	}
	
	public boolean sendOTP(String username, String code) {
		
		String url = baseUrl + "/otp/check";
		
		// 이 호출에는 HTTP 요청 본문에 사용자 이름과 암호가 필요하다.
		User body = new User();
		body.setUsername(username);
		body.setCode(code);
		
		HttpEntity request = new HttpEntity<>(body);
		
		ResponseEntity response = rest.postForEntity(url, request, Void.class);
		
		return response.getStatusCode().equals(HttpStatus.OK);
	}
}
