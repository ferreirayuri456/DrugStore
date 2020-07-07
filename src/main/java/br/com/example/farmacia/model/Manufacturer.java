package br.com.example.farmacia.model;

import java.time.LocalDateTime;

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
public class Manufacturer extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "MANUFACTURER")
	private String codeManufacturer;
	@Column(name = "FANTASY_NAME")
	private String fantasyName;
	@Column(name = "CNPJ")
	private Integer cnpj;
	@Column(name = "COUNTRY_ORIGIN")
	private String countryOrigin;

	public Manufacturer(Integer id, String codeManufacturer, String fantasyName, Integer cnpj, String countryOrigin,
			LocalDateTime dataChangeCreatedTime, LocalDateTime dataChangeLastModifiedTime) {
		this.id = id;
		this.codeManufacturer = codeManufacturer;
		this.fantasyName = fantasyName;
		this.cnpj = cnpj;
		this.countryOrigin = countryOrigin;
		this.dataChangeCreatedTime = dataChangeCreatedTime;
		this.dataChangeLastModifiedTime = dataChangeLastModifiedTime;
	}
}
