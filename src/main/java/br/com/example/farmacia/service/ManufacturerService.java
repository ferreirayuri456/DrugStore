package br.com.example.farmacia.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.example.farmacia.model.Manufacturer;
import br.com.example.farmacia.model.dto.ManufacturerDTO;

public interface ManufacturerService {

	public abstract List<ManufacturerDTO> listManufacturer();

	public abstract ManufacturerDTO storeManufacturer(ManufacturerDTO dto);

	public abstract ResponseEntity<Manufacturer> updateManufacturer(Integer code, ManufacturerDTO dto);

	public abstract ResponseEntity<?> removeManufacturer(Integer code);
}
