package com.laurentiuspilca.ssia.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * 10.1 두 엔드포인트가 있는 컨트롤러 클래스
 */
@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public String getHello() {
		
		return "Get Hello!";
	}
	
	@PostMapping("/hello")
	public String postHello() {
		
		return "Post Hello!";
	}
}
