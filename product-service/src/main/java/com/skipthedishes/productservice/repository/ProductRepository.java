package com.skipthedishes.productservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skipthedishes.productservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("FROM Product WHERE LOWER(name) LIKE :searchText")
	List<Product> findByName(@Param("searchText") String searchText);
}
