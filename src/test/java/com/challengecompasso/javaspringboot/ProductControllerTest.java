package com.challengecompasso.javaspringboot;


import com.challengecompasso.javaspringboot.model.Product;
import com.challengecompasso.javaspringboot.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;
	@MockBean
	private ProductRepository productRepository;
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void postProdutoRestTest201() {
		Product item = new Product(null, "name", "Descricao", BigDecimal.TEN);
		Product itemRetorno = new Product("3121", "name", "Descricao", BigDecimal.TEN);
		BDDMockito.when(productRepository.save(item)).thenReturn(itemRetorno);
		ResponseEntity<Product> response = restTemplate.postForEntity("/products", item, Product.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.CREATED.value());
		Assertions.assertThat(response.getBody().getId()).isNotNull();
	}

	@Test
	public void postProdutoRestTest400() {
		Product item = new Product("123", null, "Descricao", BigDecimal.TEN);
		BDDMockito.when(productRepository.save(item)).thenReturn(item);
		ResponseEntity<Product> response = restTemplate.postForEntity("/products", item, Product.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}

	@Test
	public void putTest400() throws Exception {
		Product item = new Product("123", null, "Descricao", BigDecimal.TEN);
		mockMvc.perform(put("/products/{id}", item).contentType("application/json").accept("application/json"))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void findAllTest200() throws Exception {
		Product item = new Product("135", "name", "Descricao", BigDecimal.TEN);
		BDDMockito.when(productRepository.save(item)).thenReturn(item);

		mockMvc.perform(
				MockMvcRequestBuilders.get("/products").contentType("application/json").accept("application/json"))
				.andExpect(status().isOk());

	}

}
