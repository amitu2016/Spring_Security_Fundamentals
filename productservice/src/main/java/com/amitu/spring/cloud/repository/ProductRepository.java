package com.amitu.spring.cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amitu.spring.cloud.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Product getProductById(Long productId);

}
