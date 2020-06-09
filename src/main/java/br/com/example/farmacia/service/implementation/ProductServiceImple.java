package br.com.example.farmacia.service.implementation;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.example.farmacia.model.Product;
import br.com.example.farmacia.model.dto.ProductDto;
import br.com.example.farmacia.repository.ProductRepository;
import br.com.example.farmacia.service.ProductService;
import br.com.example.farmacia.specification.SpecificationProduct;

@Service
public class ProductServiceImple implements ProductService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ProductRepository prodRepository;

	@Override
	@Transactional
	public ProductDto storeProduct(ProductDto dto) {
		return modelMapper.map(this.prodRepository.save(modelMapper.map(dto, Product.class)), ProductDto.class);
	}

	@Override
	@Transactional
	public ResponseEntity<Product> updateProduct(Integer code, ProductDto dto) {
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
	public ResponseEntity<?> removeProduct(Integer code) {
		return prodRepository.findById(code).map(mapped -> {
			prodRepository.deleteById(code);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	@Override
	@Transactional
	public List<Product> searchProducts(ProductDto dto) {
		Specification<Product> spec = SpecificationProduct.findBySpecification(dto);
		return prodRepository.findAll(spec);
	}

}
