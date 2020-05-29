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
@Table(name = "MANUFACTURER")
@Entity
public class Manufacturer{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "MANUFACTURER")
	private String codeManufacturer;
	@Column(name = "FANTASY_NAME")
	private String fantasyName;
	@Column(name = "CNPJ")
	private Long cnpj;
	@Column(name = "COUNTRY_ORIGIN")
	private String countryOrigin;
	
	public Manufacturer(String codeManufacturer, String fantasyName, Long cnpj, String countryOrigin) {
		this.codeManufacturer = codeManufacturer;
		this.fantasyName = fantasyName;
		this.cnpj = cnpj;
		this.countryOrigin = countryOrigin;
	}
}
