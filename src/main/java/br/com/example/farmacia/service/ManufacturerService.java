package br.com.example.farmacia.service;

import java.util.List;

import br.com.example.farmacia.controller.dto.ManufacturerDto;

public interface ManufacturerService {

	public abstract List<ManufacturerDto> listManufacturer();

	public abstract ManufacturerDto storeManufacturer(ManufacturerDto dto);

	public abstract ManufacturerDto updateManufacturer(Integer code, ManufacturerDto dto);

	public abstract ManufacturerDto removeManufacturer(Integer code);
}
