package br.com.example.farmacia.model.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.example.farmacia.model.Manufacturer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturerDTO {

	private Integer id;
	@NotBlank(message = "{codeManufacturer.not.blank}")
	private String codeManufacturer;
	@NotBlank(message = "{fantasyName.not.blank}")
	private String fantasyName;
	@NotNull(message = "O campo cnpj não pode ser vazio")
	private int cnpj;
	@NotBlank(message = "{countryOrigin.not.blank}")
	private String countryOrigin;

	public ManufacturerDTO(Manufacturer manufacturer) {
		this.id = manufacturer.getId();
		this.codeManufacturer = manufacturer.getCodeManufacturer();
		this.fantasyName = manufacturer.getFantasyName();
		this.cnpj = manufacturer.getCnpj();
		this.countryOrigin = manufacturer.getCountryOrigin();
	}

	public static List<ManufacturerDTO> conversor(List<Manufacturer> manufacturer) {
		return manufacturer.stream().map(ManufacturerDTO::new).collect(Collectors.toList());
	}

	public Manufacturer newManufacturer() {
		return new Manufacturer(codeManufacturer, fantasyName, cnpj, countryOrigin, null, null);
	}

	public ManufacturerDTO(@NotNull String codeManufacturer, String fantasyName, int cnpj, String countryOrigin) {
		this.codeManufacturer = codeManufacturer;
		this.fantasyName = fantasyName;
		this.cnpj = cnpj;
		this.countryOrigin = countryOrigin;
	}
}
