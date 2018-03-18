package com.skipthedishes.orderservice.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.skipthedishes.orderservice.model.Order;

public class OrderDto {
	private Long id;
	private Date date;
	private Long customerId;
	private String deliveryAddress;
	private String contact;
	private Long storeId;
	private String status;
	private Date lastUpdate;
	private BigDecimal total;
	private List<OrderItemDto> orderItems;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<OrderItemDto> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemDto> orderItems) {
		this.orderItems = orderItems;
	}

	public Order toEntity() {
		Order order = new Order();
		order.setContact(contact);
		order.setCustomerId(customerId);
		order.setDate(date);
		order.setDeliveryAddress(deliveryAddress);
		order.setId(id);
		order.setLastUpdate(lastUpdate);
		order.setStatus(status);
		order.setStoreId(storeId);
		order.setTotal(total);
		return order;
	}

	public Order toEntity(String status) {
		Order order = toEntity();
		order.setStatus(status);
		return order;
	}

	public static OrderDto from(Order order) {
		OrderDto dto = new OrderDto();
		dto.setContact(order.getContact());
		dto.setCustomerId(order.getCustomerId());
		dto.setDate(order.getDate());
		dto.setDeliveryAddress(order.getDeliveryAddress());
		dto.setId(order.getId());
		dto.setLastUpdate(order.getLastUpdate());
		dto.setStatus(order.getStatus());
		dto.setStoreId(order.getStoreId());
		dto.setTotal(order.getTotal());

		List<OrderItemDto> orderItemsDto = order.getOrderItems()
				.stream()
				.map(orderItem -> OrderItemDto.from(orderItem))
				.collect(Collectors.toList());
		
		dto.setOrderItems(orderItemsDto);

		return dto;
	}

}
