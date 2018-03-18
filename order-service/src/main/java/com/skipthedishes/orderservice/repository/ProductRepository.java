package com.skipthedishes.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skipthedishes.orderservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
