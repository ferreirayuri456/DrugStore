package br.com.example.farmacia.cucumber.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import br.com.example.farmacia.model.Product;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;

public class ManufacturerTestStepGET {

	@Dado("^eu perfomo a operacao GET com '/manufacturer'$")
	public void eu_perfomo_a_operacao_GET_com_manufacturer() throws Throwable {
		RestTemplate restTemplate = new RestTemplateBuilder().rootUri("http://localhost:8082/manufacturer/")
				.build();
	}

	@Entao("^eu deveria ver os detalhes dos fabricantes$")
	public void eu_deveria_ver_os_detalhes_dos_fabricantes() throws Throwable {
		final String uri = "http://localhost:8082/manufacturer/";

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");

		RestTemplate restTemplate = new RestTemplate();
		Product[] result = restTemplate.getForObject(uri, Product[].class, params);

		assertThat(HttpStatus.OK);
		assertNotNull(result);
	}
}
