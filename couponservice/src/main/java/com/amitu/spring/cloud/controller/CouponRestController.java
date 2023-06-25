package com.amitu.spring.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amitu.spring.cloud.model.Coupon;
import com.amitu.spring.cloud.repository.CouponRepository;

@RestController
@RequestMapping("/coupon")
@CrossOrigin
public class CouponRestController {
	
	@Autowired
	private CouponRepository repository;
	
	@PostMapping("/create")
	public Coupon createCoupon(@RequestBody Coupon coupon) {	
		return repository.save(coupon);		
	}
	
	@GetMapping("/get-coupon/{code}")
	@PostAuthorize("returnObject.discount<60")
	public Coupon getCoupon(@PathVariable("code") String code) {
		return repository.findByCode(code);
	}

}
