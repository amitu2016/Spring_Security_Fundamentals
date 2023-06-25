package com.amitu.spring.cloud.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class WebSecurityConfig {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	SecurityContextRepository securityContextRepository() {
		return new DelegatingSecurityContextRepository(new RequestAttributeSecurityContextRepository(), 
				new HttpSessionSecurityContextRepository());
	}
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return new ProviderManager(provider);
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests()
		.requestMatchers(HttpMethod.GET, "/showGetProduct","/getProduct")
		.hasAnyRole("USER","ADMIN")
		//.permitAll()
		.requestMatchers(HttpMethod.GET, "/showCreateProduct","/createProduct","/createResponse")
		.hasRole("ADMIN")
		.requestMatchers(HttpMethod.POST, "/product/create","/saveProduct")
		.hasRole("ADMIN")
		.requestMatchers(HttpMethod.POST, "/getProduct")
		.hasAnyRole("USER","ADMIN")
		.requestMatchers("/","/login","/showReg","/registerUser").permitAll()
		.and().logout().logoutSuccessUrl("/").and().csrf().disable();
		
		http.cors(corsCustomizer -> {
			CorsConfigurationSource configurationSource = request->{
				CorsConfiguration corsConfiguration = new CorsConfiguration();
				corsConfiguration.setAllowedOrigins(List.of("localhost:9091"));
				corsConfiguration.setAllowedMethods(List.of("GET"));
				return corsConfiguration;
			};
			corsCustomizer.configurationSource(configurationSource);
		});
		
		http.securityContext(context -> context.requireExplicitSave(true));
		
		return http.build();
		
	}

	
}
