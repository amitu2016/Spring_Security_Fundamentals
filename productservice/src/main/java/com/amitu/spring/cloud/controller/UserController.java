package com.amitu.spring.cloud.controller;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.amitu.spring.cloud.model.Role;
import com.amitu.spring.cloud.model.User;
import com.amitu.spring.cloud.repository.UserRepository;
import com.amitu.spring.cloud.security.SecurityService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UserController {

	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/showReg")
	public String showRegPage() {
		return "registerUser";
	}
	
	@PostMapping("/registerUser")
	public String register(User user) {
	    user.setPassword(passwordEncoder.encode(user.getPassword()));
	    HashSet<Role> roles = new HashSet<>();
	    Role role = new Role();
	    role.setId(2l);
	    roles.add(role);
	    user.setRoles(roles);
		repository.save(user);
		return "login";
	}

	@GetMapping("/")
	public String showLoginForm() {
		return "login";
	}

	@PostMapping("/login")
	public String login(String userName, String password, HttpServletRequest request, HttpServletResponse response) {
		boolean loginResponse = securityService.login(userName, password, request, response);
		
		if (loginResponse) {
			return "index";
		}
		return "login";
	}
}
