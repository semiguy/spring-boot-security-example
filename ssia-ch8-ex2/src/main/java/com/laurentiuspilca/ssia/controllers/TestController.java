package com.laurentiuspilca.ssia.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@PostMapping("/a")
	public String postEnpointA() {
		
		return "Works!";
	}
	
	@GetMapping("/a")
	public String getEnPointA() {
		
		return "Works!";
	}
	
	@GetMapping("/a/b")
	public String getEnpointB() {
		
		return "Works!";
	}
	
	@GetMapping("/a/b/c")
	public String getEnpointC() {
		
		return "Works!";
	}
}
