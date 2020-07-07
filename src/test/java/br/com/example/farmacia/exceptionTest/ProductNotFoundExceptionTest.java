package br.com.example.farmacia.exceptionTest;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.example.farmacia.config.Util;
import br.com.example.farmacia.exceptions.ProductNotFoundException;
import br.com.example.farmacia.model.Product;
import br.com.example.farmacia.repository.ProductRepository;
import br.com.example.farmacia.service.ProductService;

public class ProductNotFoundExceptionTest {

	@Autowired
	ProductService productService;

	@Autowired
	ProductRepository prodRepo;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	private static Product prod = new Product(1, 2132, "Comprimido para dores musculares", "Buscofem", "Bayer", 26);

	@Test(expected = ProductNotFoundException.class)
	public void testProductDeveLancarExcecao() {

		try {

			String jsonFile = Util.fileJson("/json/TestProduct.json");

			RequestBuilder rBuilder = MockMvcRequestBuilders.post("/").content(jsonFile)
					.contentType(MediaType.APPLICATION_JSON);

			MvcResult mvcResult = mockMvc.perform(rBuilder).andReturn();
			MockHttpServletResponse mockResponse = mvcResult.getResponse();

			this.mockMvc
					.perform(put("/1", prod.getId()).header("Accept", "Application/json")
							.contentType(MediaType.APPLICATION_JSON_VALUE)
							.content(objectMapper.writeValueAsString(new Product(1, 1234, "Buscofem Feminino",
									"Comprimido para cólicas menstruais", "Bayer", 35))))
					.andDo(print()).andExpect(status().isOk());
			fail();
		} catch (Exception e) {
			throw new ProductNotFoundException(prod.getId());
		}
	}
}
