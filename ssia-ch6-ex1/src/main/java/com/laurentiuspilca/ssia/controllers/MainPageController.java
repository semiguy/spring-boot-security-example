package com.laurentiuspilca.ssia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.laurentiuspilca.ssia.services.ProductService;

@Controller
public class MainPageController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/main")
	public String main(Authentication a, Model model) {
		
		model.addAttribute("username", a.getName());
		model.addAttribute("products", productService.findAll());
		
		return "main.html";
	}
}
