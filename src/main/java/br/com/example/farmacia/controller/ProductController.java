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

import br.com.example.farmacia.model.Product;
import br.com.example.farmacia.model.dto.ProductDto;
import br.com.example.farmacia.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Produtos")
public class ProductController {

	@Autowired
	ProductService prodService;

	
	@ApiOperation(value = "Inserir um novo produto",
			response = ProductDto.class,
			notes = "Essa informação guarda novas informações na base de dados")
	@ApiResponses(value = {
			@ApiResponse(code = 200,
					message = "Retorna uma lista de produtos",
					response = ProductDto.class),
			@ApiResponse(code = 500,
			message = "Se tivermos algum problema, retornaremos uma exceção",
			response = ProductDto.class),
			
	})
	@PostMapping("/")
	public ProductDto storeProduct(@RequestBody @Valid ProductDto dto) throws Exception {
		return prodService.storeProduct(dto);
	}

	
	@ApiOperation(value = "Lista os produtos por nome do produto e/ou nome fantasia",
			response = ProductDto.class,
			notes = "Essa operação resgata do banco de dados os produtos")
	@ApiResponses(value = {
			@ApiResponse(code = 200,
					message = "Retorna uma lista de produtos",
					response = ProductDto.class),
			@ApiResponse(code = 403,
					message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500,
					message = "Foi gerada uma exceção"),
	})
	@GetMapping("/")
	public ResponseEntity<List<Product>> searchProducts(
			@RequestParam(required = false) String nameProduct,
			@RequestParam(required = false) String fantasyName) throws Exception{
		return ResponseEntity.ok(prodService.searchProducts(new ProductDto(nameProduct, fantasyName)));
		
	}

	
	@ApiOperation(value = "Atualiza um produto existente",
			response = ProductDto.class,
			notes = "Essa operação atualiza as informações de um produto existente na base de dados")
	@ApiResponses(value = {
			@ApiResponse(code = 200,
					message = "Retorna uma lista de produtos",
					response = ProductDto.class)
	})
	@PutMapping(path = {"/{id}"})
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer code, @RequestBody @Valid ProductDto dto) throws Exception {
		return prodService.updateProduct(code, dto);
	}

	
	@ApiOperation(value = "Remove um produto existente",
			response = ProductDto.class,
			notes = "Essa operação remove o produto e suas informações da base de dados")
	@ApiResponses(value = {
			@ApiResponse(code = 200,
					message = "Retorna uma lista de produtos",
					response = ProductDto.class)
	})
	@DeleteMapping(path = {"/{id}"})
	public ResponseEntity<?> removeProduct(@PathVariable("id") Integer code) throws Exception {
		return prodService.removeProduct(code);
	}
}
