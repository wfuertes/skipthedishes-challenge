package com.skipthedishes.authservice.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skipthedishes.authservice.security.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
