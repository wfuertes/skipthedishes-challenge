package com.skipthedishes.orderservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.skipthedishes.orderservice.dto.OrderDto;
import com.skipthedishes.orderservice.model.Order;
import com.skipthedishes.orderservice.model.OrderItem;
import com.skipthedishes.orderservice.repository.OrderItemRepository;
import com.skipthedishes.orderservice.repository.OrderRepository;

@Service
public class OrderService {

	private final JmsTemplate jmsTemplate;
	private final OrderRepository orderRepository;
	private final OrderItemRepository orderItemRepository;

	@Autowired
	public OrderService(final JmsTemplate jmsTemplate, final OrderRepository orderRepository,
			final OrderItemRepository orderItemRepository) {

		this.jmsTemplate = jmsTemplate;
		this.orderRepository = orderRepository;
		this.orderItemRepository = orderItemRepository;
	}

	public boolean register(OrderDto order) {
		jmsTemplate.convertAndSend(order);
		return true;
	}

	@JmsListener(destination = "IN.ORDERS")
	public void processOrder(Message message) {

		try {
			OrderDto orderDto = (OrderDto) jmsTemplate.getMessageConverter().fromMessage(message);

			Order order = orderRepository.save(orderDto.toEntity("PROCESSED"));

			List<OrderItem> orderItems = orderDto.getOrderItems().stream().map(item -> {
				item.setId(null);
				item.setOrderId(order.getId());
				return item.toEntity();
			}).collect(Collectors.toList());

			orderItemRepository.saveAll(orderItems);
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	public Optional<Order> getOrder(Long id) {
		return orderRepository.findById(id);
	}
}
