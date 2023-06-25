package com.amitu.spring.cloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
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
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//		http.authorizeHttpRequests()
//				.requestMatchers(HttpMethod.GET, "/coupon/get-coupon/{code:^[A-z]*$}", "/index", "/showGetCoupon",
//						 "/couponDetails")
//				.hasAnyRole("USER","ADMIN")
//				.requestMatchers(HttpMethod.GET, "/showCreateCoupon", "/createCoupon", "/createResponse")
//				.hasRole("ADMIN")
//				.requestMatchers(HttpMethod.POST, "/coupon/create", "/saveCoupon","/getCoupon")
//				.hasRole("ADMIN")
//				.requestMatchers("/", "/login",  "/showReg", "/registerUser").permitAll().			
//				and().logout()
//				.logoutSuccessUrl("/").and().csrf().disable();


		http.securityContext(context -> context.requireExplicitSave(true));

		return http.build();

	}

}
