package br.com.example.farmacia;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.example.farmacia.config.Util;
import br.com.example.farmacia.controller.ProductController;
import br.com.example.farmacia.model.Product;
import br.com.example.farmacia.repository.ProductRepository;
import br.com.example.farmacia.service.ProductService;

@SpringBootTest
@AutoConfigureMockMvc
public class TestProduct {

	@Autowired
	ProductRepository prodRepository;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ProductService prodService;

	@Autowired
	ProductController prodController;

	@Autowired
	ObjectMapper objectMapper;

	private static Product prod = new Product(1, 2132, "Comprimido para dores musculares", "Buscofem", "Bayer", 26);

	@Test
	public void testStoreProduct() {
		Product prod = new Product(1, 2132, "Comprimido para dores musculares", "Buscofem", "Bayer", 26);
		this.prodRepository.save(prod);
		assertThat(prod.getId()).isNotNull();
		assertThat(prod.getCodeProduct()).isEqualTo(2132);
		assertThat(prod.getFantasyName()).isEqualTo("Buscofem");
		assertThat(prod.getNameProduct()).isEqualTo("Comprimido para dores musculares");
		assertThat(prod.getManufacturer()).isEqualTo("Bayer");
		assertThat(prod.getPrice()).isEqualTo(26);
	}

	@Test
	public void testRemoveProduct() {
		Product prod = new Product(1, 2132, "Comprimido para dores musculares", "Buscofem", "Bayer", 26);
		this.prodRepository.save(prod);
		prodRepository.delete(prod);
		assertThat(prodRepository.findById(prod.getId())).isEmpty();
	}

	@Test
	public void testUpdateProduct() {
		Product prod = new Product(1, 2132, "Comprimido para dores musculares", "Buscofem", "Bayer", 26);
		this.prodRepository.save(prod);
		prod.setCodeProduct(1234);
		prod.setFantasyName("Comprimido para dores musculares e cólicas");
		prod.setNameProduct("Dorflex + Buscofem");
		prod.setManufacturer("Global Farma");
		prod.setPrice(30);
		this.prodRepository.save(prod);
		assertThat(prod.getId()).isNotNull();
		assertThat(prod.getCodeProduct()).isEqualTo(1234);
		assertThat(prod.getFantasyName()).isEqualTo("Comprimido para dores musculares e cólicas");
		assertThat(prod.getNameProduct()).isEqualTo("Dorflex + Buscofem");
		assertThat(prod.getManufacturer()).isEqualTo("Global Farma");
		assertThat(prod.getPrice()).isEqualTo(30);
	}

	@Test
	public void testEndPointStoreProduct() {
		try {
			String jsonFile = Util.fileJson("/json/TestProduct.json");

			RequestBuilder rBuilder = MockMvcRequestBuilders.post("/product/store").accept(MediaType.APPLICATION_JSON)
					.content(jsonFile).contentType(MediaType.APPLICATION_JSON);

			MvcResult mvcResult = mockMvc.perform(rBuilder).andReturn();
			MockHttpServletResponse mvcResponse = mvcResult.getResponse();

			assertEquals(HttpStatus.OK.value(), mvcResponse.getStatus());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}


	@Test
	public void testEndPointUpdateProduct() throws Exception {
		try {

			String jsonFile = Util.fileJson("/json/TestProduct.json");

			RequestBuilder rBuilder = MockMvcRequestBuilders.post("/product/store").accept(MediaType.APPLICATION_JSON)
					.content(jsonFile).contentType(MediaType.APPLICATION_JSON);

			MvcResult mvcResult = mockMvc.perform(rBuilder).andReturn();
			MockHttpServletResponse mvcResponse = mvcResult.getResponse();

			assertEquals(HttpStatus.OK.value(), mvcResponse.getStatus());

			mockMvc.perform(put("/product/update/1", prod.getId()).header("Accept", "Application/json")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(objectMapper.writeValueAsString(new Product(1, 1234, "Buscofem Feminino",
							"Comprimido para cólicas menstruais", "Bayer", 35))))
					.andDo(print()).andExpect(status().isOk());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Não foi possível atualizar o pois: " + e.getMessage());
		}

	}

	@Test
	public void testEndPointRemove() throws Exception {
		try {

			this.mockMvc
					.perform(MockMvcRequestBuilders.delete("/product/remove/{id}")
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().is2xxSuccessful());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Não foi possível deletar o produto pois: " + e.getMessage());
		}

	}

	@Test
	public void testListProductByNameProduct() throws Exception {
		try {
			this.mockMvc.perform(get("/product/listAll?nameProduct={nameProduct}", prod.getNameProduct())
					.header("Accept", "Application/json")).andDo(print()).andExpect(status().is2xxSuccessful());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Não foi possível encontrar o produto pois: " + e.getMessage());
		}
	}

	@Test
	public void testListProductByFantasyName() throws Exception {
		try {
			this.mockMvc.perform(get("/product/listAll?fantasyName={fantasyName}", prod.getFantasyName())
					.header("Accept", "Application/json")).andDo(print()).andExpect(status().is2xxSuccessful());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Não foi possível encontrar o produto pois: " + e.getMessage());
		}
	}
}
