package com.amitu.spring.cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amitu.spring.cloud.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
