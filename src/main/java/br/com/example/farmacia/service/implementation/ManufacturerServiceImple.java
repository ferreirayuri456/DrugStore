package br.com.example.farmacia.service.implementation;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.example.farmacia.model.Manufacturer;
import br.com.example.farmacia.model.dto.ManufacturerDto;
import br.com.example.farmacia.repository.ManufacturerRepository;
import br.com.example.farmacia.service.ManufacturerService;

@Service
public class ManufacturerServiceImple implements ManufacturerService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ManufacturerRepository manuRepository;

	@Override
	@Transactional
	public List<ManufacturerDto> listManufacturer() {
		List<Manufacturer> manu = manuRepository.findAll();
		return ManufacturerDto.conversor(manu);
	}

	@Override
	@Transactional
	public ResponseEntity<Manufacturer> updateManufacturer(Integer code, ManufacturerDto dto) {
		return manuRepository.findById(code).map(mapper -> {
			mapper.setCodeManufacturer(dto.getCodeManufacturer());
			mapper.setCountryOrigin(dto.getCountryOrigin());
			mapper.setFantasyName(dto.getFantasyName());
			mapper.setCnpj(dto.getCnpj());
			Manufacturer manu = manuRepository.save(mapper);
			return ResponseEntity.ok().body(manu);
		}).orElse(ResponseEntity.notFound().build());
	}

	@Override
	@Transactional
	public ResponseEntity<?> removeManufacturer(Integer code) {
		return manuRepository.findById(code).map(mapper -> {
			manuRepository.deleteById(code);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	@Override
	@Transactional
	public ManufacturerDto storeManufacturer(ManufacturerDto dto) {
		return modelMapper.map(this.manuRepository.save(modelMapper.map(dto, Manufacturer.class)),
				ManufacturerDto.class);
	}

}
