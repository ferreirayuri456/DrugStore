package br.com.example.farmacia.service.implementation;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.example.farmacia.controller.dto.ProductDto;
import br.com.example.farmacia.model.Product;
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
		Product prod = modelMapper.map(dto, Product.class);
		return modelMapper.map(this.prodRepository.save(prod), ProductDto.class);
	}

	@Override
	@Transactional
	public ProductDto updateProduct(Integer code, ProductDto dto) {
		Product prod = dto.update(code, prodRepository);
		Product prodPersisted = prodRepository.save(modelMapper.map(prod, Product.class));
		return modelMapper.map(prodPersisted, ProductDto.class);
	}

	@Override
	@Transactional
	public ProductDto removeProduct(Integer code) {
		Product prod = prodRepository.getOne(code);
		prodRepository.deleteById(code);
		return modelMapper.map(prod, ProductDto.class);
	}

	@Override
	@Transactional
	public List<Product> searchProducts(ProductDto dto) {
		Specification<Product> spec = SpecificationProduct.findBySpecification(dto);
		return prodRepository.findAll(spec);
	}

}
