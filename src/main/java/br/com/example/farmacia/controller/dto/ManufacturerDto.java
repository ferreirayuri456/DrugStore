package br.com.example.farmacia.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.example.farmacia.model.Manufacturer;
import br.com.example.farmacia.repository.ManufacturerRepository;
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
public class ManufacturerDto {

	private Integer id;
	@NotNull
	@NotBlank(message = "{codeManufacturer.not.blank}")
	private String codeManufacturer;
	@NotNull
	@NotBlank(message = "{fantasyName.not.blank}")
	private String fantasyName;
	@NotNull
	private Long cnpj;
	@NotNull
	@NotBlank(message = "{countryOrigin.not.blank}")
	private String countryOrigin;

	public ManufacturerDto(Manufacturer manufacturer) {
		this.id = manufacturer.getId();
		this.codeManufacturer = manufacturer.getCodeManufacturer();
		this.fantasyName = manufacturer.getFantasyName();
		this.cnpj = manufacturer.getCnpj();
		this.countryOrigin = manufacturer.getCountryOrigin();
	}

	public static List<ManufacturerDto> conversor(List<Manufacturer> manufacturer) {
		return manufacturer.stream().map(ManufacturerDto::new).collect(Collectors.toList());
	}

	public Manufacturer update(Integer id, ManufacturerRepository manufactureRepository) {
		Manufacturer manu = manufactureRepository.getOne(id);
		manu.setFantasyName(this.fantasyName);
		manu.setCountryOrigin(this.countryOrigin);
		manu.setCodeManufacturer(this.codeManufacturer);
		manu.setCnpj(this.cnpj);

		return manu;
	}

	public Manufacturer newManufacturer() {
		return new Manufacturer(codeManufacturer, fantasyName, cnpj, countryOrigin);
	}

	public ManufacturerDto(@NotNull String codeManufacturer, String fantasyName, Long cnpj,
			String countryOrigin) {
		this.codeManufacturer = codeManufacturer;
		this.fantasyName = fantasyName;
		this.cnpj = cnpj;
		this.countryOrigin = countryOrigin;
	}
}
