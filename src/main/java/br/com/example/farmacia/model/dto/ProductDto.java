package br.com.example.farmacia.model.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.example.farmacia.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

	private Integer id;

	@NotNull(message = "O campo code product não pode ser vazio")
	private Integer codeProduct;
	@NotBlank(message = "{nameProduct.not.blank}")
	private String nameProduct;
	@NotBlank(message = "{fantasyName.not.blank}")
	private String fantasyName;
	@NotBlank(message = "{manufacturer.not.blank}")
	private String manufacturer;
	@NotNull(message = "O campo price não pode ser vazio")
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
