package com.skipthedishes.productservice.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skipthedishes.productservice.dto.ProductDto;
import com.skipthedishes.productservice.service.ProductService;

@RestController
@RequestMapping(path = "/v1/products")
public class ProductCtrl {

	private ProductService productService;

	@Autowired
	public ProductCtrl(final ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<?> getProducts() {

		List<ProductDto> products = productService.findAll();
		if (products.size() > 0) {
			return ResponseEntity.ok(products);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping(path = "/search/{searchText}")
	public ResponseEntity<?> searchProduct(@PathVariable String searchText) {

		List<ProductDto> products = productService.searchByText(searchText);
		if (products.size() > 0) {
			return ResponseEntity.ok(products);
		}
		return ResponseEntity.notFound().build();
	}
}
