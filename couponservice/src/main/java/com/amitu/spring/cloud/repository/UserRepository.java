package com.amitu.spring.cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amitu.spring.cloud.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
}
