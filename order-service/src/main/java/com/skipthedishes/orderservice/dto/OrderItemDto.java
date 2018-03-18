package com.skipthedishes.orderservice.dto;

import java.math.BigDecimal;

import com.skipthedishes.orderservice.model.OrderItem;

public class OrderItemDto {
	private Long id;
	private Long orderId;
	private Long productId;
	private BigDecimal price;
	private Long quantity;
	private BigDecimal total;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	public OrderItem toEntity() {
		OrderItem orderItem = new OrderItem();
		orderItem.setId(this.id);
		orderItem.setOrderId(this.orderId);
		orderItem.setPrice(this.price);
		orderItem.setProductId(this.productId);
		orderItem.setQuantity(this.quantity);
		orderItem.setTotal(this.total);
		return orderItem;			
	}
	
	public static OrderItemDto from (OrderItem orderItem) {
		OrderItemDto dto = new OrderItemDto();
		dto.setId(orderItem.getId());
		dto.setOrderId(orderItem.getOrderId());
		dto.setPrice(orderItem.getPrice());
		dto.setProductId(orderItem.getProductId());
		dto.setQuantity(orderItem.getQuantity());
		dto.setTotal(orderItem.getTotal());
		return dto;
	}

}
