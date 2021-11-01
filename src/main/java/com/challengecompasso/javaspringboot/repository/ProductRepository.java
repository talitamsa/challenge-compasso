package com.challengecompasso.javaspringboot.repository;

import com.challengecompasso.javaspringboot.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, String> {

	List<Product> findByPriceBetweenAndNameAndDescription(BigDecimal priceMin, BigDecimal priceMax, String name,
														  String description);
}