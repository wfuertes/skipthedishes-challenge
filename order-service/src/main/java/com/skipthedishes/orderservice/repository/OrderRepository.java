package com.skipthedishes.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skipthedishes.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
