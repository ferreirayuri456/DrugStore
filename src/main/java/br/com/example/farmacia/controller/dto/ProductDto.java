package br.com.example.farmacia.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import br.com.example.farmacia.model.Product;
import br.com.example.farmacia.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
	
	private Integer id;
	@NotNull
	private Integer codeProduct;
	@NotNull
	private String nameProduct;
	@NotNull
	private String fantasyName;
	@NotNull
	private String manufacturer;
	@NotNull
	private int price;

	public ProductDto(Product product) {
		this.id = product.getId();
		this.codeProduct = product.getCodeProduct();
		this.fantasyName = product.getFantasyName();
		this.nameProduct = product.getNameProduct();
		this.manufacturer = product.getManufacturer();
		this.price = product.getPrice();
	}

	/**
	 * API Stream converte a lista; map vai transformar o objeto model em um dto
	 * collect(toList) vai gerar uma lista de valores
	 * 
	 * @param products
	 * @return
	 */
	public static List<ProductDto> conversor(List<Product> products) {
		return products.stream().map(ProductDto::new).collect(Collectors.toList());
	}

	public Product update(Integer id, ProductRepository productRepository) {
		Product product = productRepository.getOne(id);
		product.setCodeProduct(this.codeProduct);
		product.setFantasyName(this.fantasyName);
		product.setNameProduct(this.nameProduct);
		product.setPrice(this.price);
		product.setManufacturer(this.manufacturer);

		return product;
	}

	public Product newProduct() {
		return new Product(codeProduct, fantasyName, nameProduct, price, manufacturer);
	}

	public ProductDto(Integer codeProduct, String fantasyName, String nameProduct, int price) {
		this.codeProduct = codeProduct;
		this.fantasyName = fantasyName;
		this.nameProduct = nameProduct;
		this.price = price;
	}

	public ProductDto(String nameProduct, String fantasyName) {
		this.nameProduct = nameProduct;
		this.fantasyName = fantasyName;
		
	}
}
