package br.com.example.farmacia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.farmacia.model.Product;
import br.com.example.farmacia.model.dto.ProductDTO;
import br.com.example.farmacia.service.ProductService;
import io.swagger.annotations.Api;

@RestController
@Api(value = "Produtos")
public class ProductController {

	@Autowired
	ProductService prodService;

	@PostMapping("/")
	public ProductDTO storeProducts(@RequestBody @Valid ProductDTO dto) throws Exception {
		prodService.sendToConsumer(dto);
		return prodService.storeProducts(dto);
		
	}

	@GetMapping("/")
	public ResponseEntity<List<Product>> searchProducts(@RequestParam(required = false) String nameProduct,
			@RequestParam(required = false) String fantasyName) throws Exception {
		return ResponseEntity.ok(prodService.searchProducts(new ProductDTO(nameProduct, fantasyName)));

	}

	@PutMapping(path = { "/{id}" })
	public ResponseEntity<Product> updateProducts(@PathVariable("id") Integer code, @RequestBody @Valid ProductDTO dto)
			throws Exception {
		return prodService.updateProducts(code, dto);
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> removeProducts(@PathVariable("id") Integer code) throws Exception {
		return prodService.removeProducts(code);
	}
	
	
}
