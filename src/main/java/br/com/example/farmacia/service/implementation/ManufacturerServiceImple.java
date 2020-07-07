package br.com.example.farmacia.service.implementation;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.example.farmacia.amqp.AmqpProducer;
import br.com.example.farmacia.exceptions.ManufacturerNotFoundException;
import br.com.example.farmacia.model.Manufacturer;
import br.com.example.farmacia.model.dto.ManufacturerDTO;
import br.com.example.farmacia.repository.ManufacturerRepository;
import br.com.example.farmacia.service.ManufacturerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Service
public class ManufacturerServiceImple implements ManufacturerService {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ManufacturerRepository manuRepository;

	@Autowired
	private AmqpProducer<ManufacturerDTO> producer;

	@Override
	@Transactional
	@ApiOperation(value = "Lista os fabricantes", response = ManufacturerDTO.class, notes = "Essa operação resgata do banco de dados os fabricantes")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna a confirmação da inserção do produto", response = Manufacturer.class),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção", response = Exception.class), })
	public List<ManufacturerDTO> listManufacturer() {
		List<Manufacturer> manu = manuRepository.findAll();
		return ManufacturerDTO.conversor(manu);
	}

	@Override
	@Transactional
	@ApiOperation(value = "Atualiza um fabricante existente", response = ManufacturerDTO.class, notes = "Essa operação atualiza o fabricante e suas informações da base de dados")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna uma lista com os fabricantes", response = Manufacturer.class),
			@ApiResponse(code = 500, message = "Caso tenhamos algum erro vamos retornar uma exceção genérica", response = Exception.class) })
	public ResponseEntity<Manufacturer> updateManufacturer(Integer code, ManufacturerDTO dto) {
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
	@ApiOperation(value = "Remove um fabricante existente", response = ManufacturerDTO.class, notes = "Essa operação remove o fabricante e suas informações da base de dados")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna uma lista de fabricante", response = Manufacturer.class),
			@ApiResponse(code = 500, message = "Caso tenhamos algum erro vamos retornar uma exceção genérica", response = Exception.class) })
	public ResponseEntity<?> removeManufacturer(Integer code) {
		return manuRepository.findById(code).map(mapper -> {
			manuRepository.deleteById(code);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ManufacturerNotFoundException(code));
	}

	@Override
	@Transactional
	@ApiOperation(value = "Inserir um novo fabricante", response = ManufacturerDTO.class, notes = "Essa operação guarda novas informações na base de dados")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna uma lista de fabricantes", response = Manufacturer.class),
			@ApiResponse(code = 500, message = "Se tivermos algum problema, retornaremos uma exceção", response = Exception.class),

	})
	public ManufacturerDTO storeManufacturer(ManufacturerDTO dto) {
		return modelMapper.map(this.manuRepository.save(modelMapper.map(dto, Manufacturer.class)),
				ManufacturerDTO.class);
	}

	@Override
	public void sendToConsumer(ManufacturerDTO dto) {
		producer.producer(dto);
	}

}
