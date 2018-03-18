package com.skipthedishes.orderservice.dto;

import java.math.BigDecimal;

import com.skipthedishes.orderservice.model.Product;

public class ProductDto {
	private Long id;
	private Long storeId;
	private String name;
	private String description;
	private BigDecimal price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public Product buildProduct() {
		Product product = new Product();
		product.setDescription(this.description);
		product.setId(this.id);
		product.setName(this.name);
		product.setPrice(this.price);
		product.setStoreId(this.storeId);
		return product;
	}

}
