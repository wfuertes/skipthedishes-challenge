package com.skipthedishes.productservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skipthedishes.productservice.dto.ProductDto;
import com.skipthedishes.productservice.model.Product;
import com.skipthedishes.productservice.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository repository;

	@Autowired
	public ProductService(final ProductRepository repository) {
		this.repository = repository;
	}

	public List<ProductDto> searchByText(String searchText) {

		List<Product> products = repository.findByName("%" + searchText.toLowerCase() + "%");
		List<ProductDto> productsDto = products.stream().map(product -> ProductDto.from(product))
				.collect(Collectors.toList());

		return productsDto;
	}

	public List<ProductDto> findAll() {

		List<Product> products = repository.findAll();
		List<ProductDto> productsDto = products.stream().map(product -> ProductDto.from(product))
				.collect(Collectors.toList());
		return productsDto;
	}

}
