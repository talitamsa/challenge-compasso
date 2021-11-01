package com.challengecompasso.javaspringboot.controller;


import com.challengecompasso.javaspringboot.model.Product;
import com.challengecompasso.javaspringboot.service.ProductService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@RestController
@Log4j2
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation(value = "Criação de um produto", response = Product.class)
	public ResponseEntity<?> productsPost(@Valid @RequestBody Product item) {
		return productService.save(item);
	}

	@PutMapping(path = "/{id}")
	@ApiOperation(value = "Atualização de um produto", response = Product.class)
	public ResponseEntity<?> productsPut(@PathVariable("id") String id,@Valid @RequestBody Product item) {
		return productService.updateById(id, item);
	}

	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Busca de um produto por ID", response = Product.class)
	public ResponseEntity<?> getProdutoPorId(@PathVariable("id") String id) {
		return productService.findById(id);
	}

	@GetMapping
	@ApiOperation(value = "Lista de produtos", response = Product.class)
	public ResponseEntity<?> getProdutos() {
		return productService.findAll();
	}

	@GetMapping("/search")
	@ApiOperation(value = "Lista de produtos filtrados", response = Product.class)
	public ResponseEntity<?> getProdutosFiltrados(@RequestParam("q") String q,
			@RequestParam("min_price") BigDecimal minPrice, @RequestParam("max_price") BigDecimal maxPrice) {
		return productService.findByFiltros(q, minPrice, maxPrice);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleção de um produto", response = Product.class)
	public ResponseEntity<?> deleteProduto(@NotBlank @PathVariable("id") String id) {
		return productService.delete(id);
	}

}
