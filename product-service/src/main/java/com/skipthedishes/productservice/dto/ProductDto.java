package com.skipthedishes.productservice.dto;

import java.math.BigDecimal;

import com.skipthedishes.productservice.model.Product;

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
	
	public Product toEntity() {
		Product product = new Product();
		product.setDescription(this.description);
		product.setId(this.id);
		product.setName(this.name);
		product.setPrice(this.price);
		product.setStoreId(this.storeId);
		return product;
	}
	
	public static ProductDto from (Product product) {
		ProductDto dto = new ProductDto();
		dto.setDescription(product.getDescription());
		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setPrice(product.getPrice());
		dto.setStoreId(product.getStoreId());
		return dto;
	}

}
