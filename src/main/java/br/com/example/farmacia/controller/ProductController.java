package br.com.example.farmacia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.farmacia.controller.dto.ProductDto;
import br.com.example.farmacia.model.Product;
import br.com.example.farmacia.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ProductController {

	@Autowired
	ProductService prodService;

	
	@ApiOperation(value = "Store new product",
			response = ProductDto.class,
			notes = "This operation store a new product with informations in database")
	@ApiResponses(value = {
			@ApiResponse(code = 200,
					message = "Return an ProductDto with informations a product",
					response = ProductDto.class),
			@ApiResponse(code = 500,
			message = "If we have anyone problem returns a Exception",
			response = ProductDto.class),
			
	})
	@PostMapping("/product/store")
	public ProductDto storeProduct(@RequestBody @Valid ProductDto dto) throws Exception {
		return prodService.storeProduct(dto);
	}

	
	@ApiOperation(value = "List an existing product by name product and fantasy name",
			response = ProductDto.class,
			notes = "This operation list an existing product with information in database")
	@ApiResponses(value = {
			@ApiResponse(code = 200,
					message = "Return an ProductDto with informations a product",
					response = ProductDto.class)
	})
	@GetMapping("/product/listAll")
	public ResponseEntity<List<Product>> searchProducts(
			@RequestParam(required = false) String nameProduct,
			@RequestParam(required = false) String fantasyName) throws Exception{
		return ResponseEntity.ok(prodService.searchProducts(new ProductDto(nameProduct, fantasyName)));
		
	}

	
	@ApiOperation(value = "Update an existing product",
			response = ProductDto.class,
			notes = "This operation updates an existing product with information in database")
	@ApiResponses(value = {
			@ApiResponse(code = 200,
					message = "Return an ProductDto with informations a product",
					response = ProductDto.class)
	})
	@PutMapping("/product/update/{id}")
	public ProductDto updateProduct(@PathVariable("id") Integer code, @RequestBody @Valid ProductDto dto) throws Exception {
		return prodService.updateProduct(code, dto);
	}

	
	@ApiOperation(value = "Remove an existing product",
			response = ProductDto.class,
			notes = "This operation removes an existing product with information in database")
	@ApiResponses(value = {
			@ApiResponse(code = 200,
					message = "Return an ProductDto with informations a product",
					response = ProductDto.class)
	})
	@DeleteMapping("/product/remove/{id}")
	public ProductDto removeProduct(@PathVariable("id") Integer code) throws Exception {
		return prodService.removeProduct(code);
	}
}
