package br.com.example.farmacia.exceptionTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.example.farmacia.config.Util;
import br.com.example.farmacia.exceptions.ManufacturerNotFoundException;
import br.com.example.farmacia.model.Manufacturer;

public class ManufacturerNotFoundExceptionTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	private static Manufacturer manu = new Manufacturer(1, "Dorflex", "Dorflex", 1234567, "Brazil");
	
	@Test(expected = ManufacturerNotFoundException.class)
	public void testManufacturerDeveLancarExcecao() throws Exception {
		try {
			String jsonFile = Util.fileJson("/json/TestManufacturer.json");

			RequestBuilder rBuilder = MockMvcRequestBuilders.post("/manufacturer/").content(jsonFile)
					.contentType(MediaType.APPLICATION_JSON);

			MvcResult mvcResult = mockMvc.perform(rBuilder).andReturn();
			MockHttpServletResponse mockResponse = mvcResult.getResponse();

			assertEquals(HttpStatus.OK.value(), mockResponse.getStatus());

			mockMvc.perform(put("/manufacturer/1", manu.getId()).header("Accept", "Application/json")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(objectMapper
							.writeValueAsString(new Manufacturer(1, "Dorflex", "Dorflex", 1234567, "Brazil"))))
					.andDo(print()).andExpect(status().isOk());
		} catch (Exception e) {
			throw new ManufacturerNotFoundException(manu.getId());
		}
	}
}
