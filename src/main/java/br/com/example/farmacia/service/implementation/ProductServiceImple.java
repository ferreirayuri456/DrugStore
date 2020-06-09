package br.com.example.farmacia.service.implementation;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.example.farmacia.model.Product;
import br.com.example.farmacia.model.dto.ProductDTO;
import br.com.example.farmacia.repository.ProductRepository;
import br.com.example.farmacia.service.ProductService;
import br.com.example.farmacia.specification.SpecificationProduct;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Service
public class ProductServiceImple implements ProductService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ProductRepository prodRepository;

	@Override
	@Transactional
	@ApiOperation(value = "Inserir um novo produto", response = ProductDTO.class, notes = "Essa informação guarda novas informações na base de dados")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna uma lista de produtos", response = ProductDTO.class),
			@ApiResponse(code = 500, message = "Se tivermos algum problema, retornaremos uma exceção", response = Exception.class),

	})
	public ProductDTO storeProducts(ProductDTO dto) {
		return modelMapper.map(this.prodRepository.save(modelMapper.map(dto, Product.class)), ProductDTO.class);
	}

	@Override
	@Transactional
	@ApiOperation(value = "Atualiza um produto existente", response = Product.class, notes = "Essa operação atualiza o produto e suas informações da base de dados")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna uma lista com os produtos", response = Product.class),
			@ApiResponse(code = 500, message = "Caso tenhamos algum erro vamos retornar uma exceção genérica", response = Exception.class) })

	public ResponseEntity<Product> updateProducts(Integer code, ProductDTO dto) {
		return prodRepository.findById(code) // Verifico se existe o id do produto que quero atualizar
				.map(mapped -> { // Realizo as atualizações necessárias
					mapped.setCodeProduct(dto.getCodeProduct());
					mapped.setFantasyName(dto.getFantasyName());
					mapped.setManufacturer(dto.getManufacturer());
					mapped.setNameProduct(dto.getNameProduct());
					mapped.setPrice(dto.getPrice());
					Product updated = prodRepository.save(mapped); // Salvo os produtos atualizados
					return ResponseEntity.ok().body(updated); // Retorno com os produtos atualizados, se for encontrado
																// retorno um 200
				}).orElse(ResponseEntity.notFound().build()); // Se o id não for encontrado, retorno um 404
	}

	@Override
	@Transactional
	@ApiOperation(value = "Remove um produto existente", response = Product.class, notes = "Essa operação remove o produto e suas informações da base de dados")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna uma lista de produtos", response = Product.class),
			@ApiResponse(code = 500, message = "Caso tenhamos algum erro vamos retornar uma exceção genérica", response = Exception.class) })
	public ResponseEntity<?> removeProducts(Integer code) {
		return prodRepository.findById(code).map(mapped -> {
			prodRepository.deleteById(code);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	@Override
	@Transactional
	@ApiOperation(value = "Lista os produtos por nome do produto e/ou nome fantasia", response = ProductDTO.class, notes = "Essa operação resgata do banco de dados os produtos")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna a confirmação da inserção do produto", response = Product.class),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção", response = Exception.class), })
	public List<Product> searchProducts(ProductDTO dto) {
		Specification<Product> spec = SpecificationProduct.findBySpecification(dto);
		return prodRepository.findAll(spec);
	}

}
