package br.com.example.farmacia.service.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.example.farmacia.controller.dto.ManufacturerDto;
import br.com.example.farmacia.model.Manufacturer;
import br.com.example.farmacia.repository.ManufacturerRepository;
import br.com.example.farmacia.service.ManufacturerService;

@Service
public class ManufacturerServiceImple implements ManufacturerService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ManufacturerRepository manuRepository;

	@Override
	public List<ManufacturerDto> listManufacturer() {
		List<Manufacturer> manu = manuRepository.findAll();
		return ManufacturerDto.conversor(manu);
	}

	@Override
	public ManufacturerDto updateManufacturer(Integer code, ManufacturerDto dto) {
		Manufacturer manu = dto.update(code, manuRepository);
		Manufacturer manuPersisted = manuRepository.save(modelMapper.map(manu, Manufacturer.class));
		return modelMapper.map(manuPersisted, ManufacturerDto.class);
	}

	@Override
	public ManufacturerDto removeManufacturer(Integer code) {
		Manufacturer manu = manuRepository.getOne(code);
		manuRepository.deleteById(code);
		return modelMapper.map(manu, ManufacturerDto.class);
	}

	@Override
	public ManufacturerDto storeManufacturer(ManufacturerDto dto) {
		Manufacturer manu = modelMapper.map(dto, Manufacturer.class);
		return modelMapper.map(this.manuRepository.save(manu), ManufacturerDto.class);
	}

}
