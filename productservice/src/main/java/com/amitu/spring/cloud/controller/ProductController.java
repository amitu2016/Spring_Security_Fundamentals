package com.amitu.spring.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.amitu.spring.cloud.model.Product;
import com.amitu.spring.cloud.repository.ProductRepository;

@Controller
public class ProductController {

	@Autowired
	private ProductRepository repo;
	
	@Value("${couponService.url}")
	private String couponServiceUrl;

	@GetMapping("/showCreateProduct")
	public String showCreateCoupon() {
		return "createProduct";
	}

	@PostMapping("/saveProduct")
	public String save(Product product) {
		
		repo.save(product);
		return "createResponse";
	}

	@GetMapping("/showGetProduct")
	public String showGetProduct() {
		return "getProduct";
	}

	@PostMapping("/getProduct")
	public ModelAndView getProduct(long id) {
		ModelAndView mav = new ModelAndView("productDetails");
		System.out.println(id);
		mav.addObject(repo.getProductById(id));
		return mav;
	}

}
