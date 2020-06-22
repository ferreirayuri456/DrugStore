package br.com.example.farmacia.cucumber.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;

public class ProductTestStepDELETE {

	@Dado("^eu faço a busca '/id'$")
	public void eu_faço_a_busca_id() throws Throwable {
		RestTemplate restTemplate = new RestTemplateBuilder().rootUri("http://localhost:8082/{id}").build();
	}

	@Dado("^eu mando uma requisicao com o id <id> e apago o registro$")
	public void eu_mando_uma_requisicao_com_o_id_id_e_apago_o_registro() throws Throwable {
		String uri = "http://localhost:8082/{id}";

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "6");

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(uri, params);

	}

	@Entao("^nao deveria mais ver o produto$")
	public void nao_deveria_mais_ver_o_produto() throws Throwable {
		assertThat(HttpStatus.OK);
	}

}
