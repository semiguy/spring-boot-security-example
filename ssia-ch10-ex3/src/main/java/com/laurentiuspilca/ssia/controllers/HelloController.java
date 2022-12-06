package com.laurentiuspilca.ssia.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public String getHello() {
		
		return "Get Hello!";
	}
	
	// /hello 경로에는 CSRF 보호가 적용된다.
	// CSRF 토큰이 없으면 엔드포인트를 호출할 수 없다.
	@PostMapping("/hello")
	public String postHello() {
		
		return "Post Hello!";
	}
	
	// /ciao 경로는 CSRF 토큰 없이 호출할 수 있다.
	@PostMapping("/ciao")
	public String postCiao() {
		
		return "Post Ciao";
	}
	
}
