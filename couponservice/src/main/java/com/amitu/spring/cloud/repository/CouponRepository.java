package com.amitu.spring.cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amitu.spring.cloud.model.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

	Coupon findByCode(String code);

}
