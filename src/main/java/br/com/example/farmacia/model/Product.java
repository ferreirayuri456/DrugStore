package br.com.example.farmacia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "PRODUCT")
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "CODE_PRODUCT")
	private Integer codeProduct;
	@Column(name = "NAME_PRODUCT")
	private String nameProduct;
	@Column(name = "FANTASY_NAME")
	private String fantasyName;
	@Column(name = "MANUFACTURER")
	private String manufacturer;
	@Column(name = "PRICE")
	private int price;

	public Product(Integer codeProduct, String fantasyName, String nameProduct, int price, String manufacturer) {
		this.codeProduct = codeProduct;
		this.fantasyName = fantasyName;
		this.nameProduct = nameProduct;
		this.price = price;
		this.manufacturer = manufacturer;
	}
}
