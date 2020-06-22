package br.com.example.farmacia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.farmacia.model.Manufacturer;
import br.com.example.farmacia.model.dto.ManufacturerDTO;
import br.com.example.farmacia.service.ManufacturerService;

@RestController
public class ManufacturerController {

	@Autowired
	ManufacturerService manuService;

	@PostMapping("manufacturer/")
	public ManufacturerDTO storeManufacturers(@RequestBody @Valid ManufacturerDTO dto) throws Exception {
		manuService.sendToConsumer(dto);
		return manuService.storeManufacturer(dto);
	}

	@GetMapping("manufacturer/")
	public List<ManufacturerDTO> listManufacturers() throws Exception {
		return manuService.listManufacturer();
	}

	@PutMapping("manufacturer/{id}")
	public ResponseEntity<Manufacturer> updateManufacturers(@PathVariable("id") Integer code,
			@RequestBody @Valid ManufacturerDTO dto) throws Exception {
		return manuService.updateManufacturer(code, dto);
	}

	@DeleteMapping(path = { "manufacturer/{id}" })
	public ResponseEntity<?> removeManufacturers(@PathVariable("id") Integer code) throws Exception {
		return manuService.removeManufacturer(code);
	}
}
