package br.com.example.farmacia.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.example.farmacia.model.Manufacturer;
import br.com.example.farmacia.model.dto.ManufacturerDto;

public interface ManufacturerService {

	public abstract List<ManufacturerDto> listManufacturer();

	public abstract ManufacturerDto storeManufacturer(ManufacturerDto dto);

	public abstract ResponseEntity<Manufacturer> updateManufacturer(Integer code, ManufacturerDto dto);

	public abstract ResponseEntity<?> removeManufacturer(Integer code);
}
