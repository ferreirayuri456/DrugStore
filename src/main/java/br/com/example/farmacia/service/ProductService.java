package br.com.example.farmacia.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.example.farmacia.model.Product;
import br.com.example.farmacia.model.dto.ProductDTO;

public interface ProductService {

	public abstract List<Product> searchProducts(ProductDTO dto);

	public abstract ProductDTO storeProducts(ProductDTO dto);

	public abstract ResponseEntity<Product> updateProducts(Integer code, ProductDTO dto);

	public abstract ResponseEntity<?> removeProducts(Integer code);
	
	void sendToConsumer(ProductDTO dto);
}
