package com.amitu.spring.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.amitu.spring.cloud.dto.Coupon;
import com.amitu.spring.cloud.model.Product;
import com.amitu.spring.cloud.repository.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductRestController {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${couponService.url}")
	private String couponServiceUrl;

	@PostMapping("/create")
	public Product createProduct(@RequestBody Product product) {
		Coupon coupon = restTemplate.getForObject(couponServiceUrl + product.getCouponCode(), Coupon.class);
		product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		return repository.save(product);
	}

	@GetMapping("/get-product/{productId}")
	public Product getProduct(@PathVariable("productId") Long productId) {
		return repository.getProductById(productId);
	}

}
