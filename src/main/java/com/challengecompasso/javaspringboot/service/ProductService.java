package com.challengecompasso.javaspringboot.service;

import com.challengecompasso.javaspringboot.model.Product;
import com.challengecompasso.javaspringboot.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public ResponseEntity<?> save(Product item) {
		return new ResponseEntity<>(productRepository.save(item), HttpStatus.CREATED);
	}

	public ResponseEntity<?> updateById(String id, Product item) {
		item.setId(id);
		return new ResponseEntity<>(productRepository.save(item), HttpStatus.OK);
	}

	public ResponseEntity<?> findById(String id) {
		ResponseEntity<?> retorno = null;
		retorno = new ResponseEntity<>(productRepository.findById(id), HttpStatus.OK);
		if (retorno.getBody().equals(Optional.empty())) {
			retorno = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return retorno;
	}

	public ResponseEntity<?> findAll() {

		return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<?> delete(String id) {
		ResponseEntity<?> retorno = new ResponseEntity<>(HttpStatus.OK);
		try {
			productRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			retorno = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return retorno;
	}

	public ResponseEntity<?> findByFiltros(String q, BigDecimal minPrice, BigDecimal maxPrice) {
		return new ResponseEntity<>(productRepository.findByPriceBetweenAndNameAndDescription(minPrice, maxPrice, q, q), HttpStatus.OK);
	}

}
