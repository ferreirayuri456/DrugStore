package br.com.example.farmacia;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.example.farmacia.config.Util;
import br.com.example.farmacia.model.Manufacturer;
import br.com.example.farmacia.repository.ManufacturerRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class TestManufacturer {

	@Autowired
	ManufacturerRepository manuRepository;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;
	private static Manufacturer manu = new Manufacturer(1, "Dorflex", "Dorflex",1234567, "Brazil");

	@Test
	public void testStoreManufacturer() {
		Manufacturer manu = new Manufacturer(1, "Dorflex", "Dorflex",1234567, "Brazil");
		this.manuRepository.save(manu);
		assertThat(manu.getId()).isNotNull();
		assertThat(manu.getCodeManufacturer()).isEqualTo("Dorflex");
		assertThat(manu.getFantasyName()).isEqualTo("Dorflex");
		assertThat(manu.getCnpj()).isEqualTo(1234567);
		assertThat(manu.getCountryOrigin()).isEqualTo("Brazil");

	}

	@Test
	public void testRemoveManufacturer() {
		Manufacturer manu = new Manufacturer(1, "Dorflex", "Dorflex",1234567, "Brazil");
		this.manuRepository.save(manu);
		manuRepository.delete(manu);
		assertThat(manuRepository.findById(manu.getId())).isEmpty();
	}

	@Test
	public void testUpdateManufacturer() {
		Manufacturer manu = new Manufacturer(1, "Dorflex", "Dorflex", 1234567, "Brazil");
		this.manuRepository.save(manu);
		manu.setCodeManufacturer("Buscofem");
		manu.setFantasyName("Comprimido para cólicas menstruais");
		manu.setCnpj(12365489);
		manu.setCountryOrigin("EUA");
		this.manuRepository.save(manu);
		assertThat(manu.getId()).isNotNull();
		assertThat(manu.getCodeManufacturer()).isEqualTo("Buscofem");
		assertThat(manu.getFantasyName()).isEqualTo("Comprimido para cólicas menstruais");
		assertThat(manu.getCnpj()).isEqualTo(12365489);
		assertThat(manu.getCountryOrigin()).isEqualTo("EUA");
	}

	@Test
	public void testEndPointStoreManufacturer() throws Exception {
		try {
			String jsonFile = Util.fileJson("/json/TestManufacturer.json");

			RequestBuilder rBuilder = MockMvcRequestBuilders.post("/manufacturer/store").content(jsonFile)
					.contentType(MediaType.APPLICATION_JSON);

			MvcResult mvcResult = mockMvc.perform(rBuilder).andReturn();
			MockHttpServletResponse mockResponse = mvcResult.getResponse();

			assertEquals(HttpStatus.OK.value(), mockResponse.getStatus());

		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Não foi possível incluir o fabricante pois o seguinte erro foi lançado: " + e.getMessage());
		}
	}

	@Test
	public void testEndPointListManufacturer() throws Exception {
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/manufacturer/list"))
					.andExpect(MockMvcResultMatchers.status().isOk());
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Não foi possível remover o fabricante pois: " + e.getMessage());
		}
	}

	@Test
	public void testEndPointUpdateManufacturer() throws Exception{
		try {
			String jsonFile = Util.fileJson("/json/TestManufacturer.json");

			RequestBuilder rBuilder = MockMvcRequestBuilders.post("/manufacturer/store").content(jsonFile)
					.contentType(MediaType.APPLICATION_JSON);

			MvcResult mvcResult = mockMvc.perform(rBuilder).andReturn();
			MockHttpServletResponse mockResponse = mvcResult.getResponse();
			
			assertEquals(HttpStatus.OK.value(), mockResponse.getStatus());
			
			mockMvc.perform(put("/manufacturer/update/1", manu.getId()).header("Accept", "Application/json")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(objectMapper.writeValueAsString(new Manufacturer(1, "Dorflex", "Dorflex",1234567, "Brazil"))))
					.andDo(print()).andExpect(status().isOk());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Não foi possível atualizar o fabricante pois: " + e.getMessage());
		}
	}

}
