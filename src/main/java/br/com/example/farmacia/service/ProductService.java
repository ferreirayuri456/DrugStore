package br.com.example.farmacia.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.example.farmacia.model.Product;
import br.com.example.farmacia.model.dto.ProductDto;

public interface ProductService {

	public abstract List<Product> searchProducts(ProductDto dto);

	public abstract ProductDto storeProduct(ProductDto dto);

	public abstract ResponseEntity<Product> updateProduct(Integer code, ProductDto dto);

	public abstract ResponseEntity<?> removeProduct(Integer code);
}
