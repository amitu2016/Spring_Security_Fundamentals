package com.amitu.spring.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public String getHello() {
		return "Spring Security Course";
		
	}
	
	@GetMapping("/bye")
	public String getBye() {
		return "Get Lost!!";
		
	}

}
