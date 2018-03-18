package com.skipthedishes.orderservice.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skipthedishes.orderservice.dto.OrderDto;
import com.skipthedishes.orderservice.model.Order;
import com.skipthedishes.orderservice.service.OrderService;

@RestController
@RequestMapping(path = "/v1/orders")
public class OrderCtrl {

	private OrderService orderService;

	@Autowired
	public OrderCtrl(final OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping
	public ResponseEntity<?> register(@RequestBody OrderDto order) {

		if (orderService.register(order)) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getOrder(@PathVariable(name = "id") Long id) {

		Optional<Order> order = orderService.getOrder(id);
		if (order.isPresent()) {
			return ResponseEntity.ok(OrderDto.from(order.get()));
		}
		return ResponseEntity.notFound().build();
	}
}
