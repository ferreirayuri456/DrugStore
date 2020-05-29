package br.com.example.farmacia.service;

import java.util.List;

import br.com.example.farmacia.controller.dto.ProductDto;
import br.com.example.farmacia.model.Product;

public interface ProductService {

	public abstract List<Product> searchProducts(ProductDto dto);

	public abstract ProductDto storeProduct(ProductDto dto);

	public abstract ProductDto updateProduct(Integer code, ProductDto dto);

	public abstract ProductDto removeProduct(Integer code);
}
