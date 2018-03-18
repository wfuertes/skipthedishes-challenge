package com.skipthedishes.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skipthedishes.orderservice.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
