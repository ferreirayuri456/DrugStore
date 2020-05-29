package br.com.example.farmacia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.farmacia.controller.dto.ManufacturerDto;
import br.com.example.farmacia.service.ManufacturerService;

@RestController
public class ManufacturerController {

	@Autowired
	ManufacturerService manuService;

	@PostMapping("/manufacturer/store")
	public ManufacturerDto storeManufacturer(@RequestBody @Valid ManufacturerDto dto) throws Exception {
		return manuService.storeManufacturer(dto);
	}

	@GetMapping("/manufacturer/list")
	public List<ManufacturerDto> listManufacturer() throws Exception {
		return manuService.listManufacturer();
	}

	@PutMapping("/manufacturer/update/{id}")
	public ManufacturerDto updateManufacturer(@PathVariable("id") Integer code,
			@RequestBody @Valid ManufacturerDto dto) throws Exception {
		return manuService.updateManufacturer(code, dto);
	}

	@DeleteMapping("/manufacturer/remove/{id}")
	public ManufacturerDto removeManufacturer(@PathVariable("id") Integer code) throws Exception {
		return manuService.removeManufacturer(code);
	}
}
