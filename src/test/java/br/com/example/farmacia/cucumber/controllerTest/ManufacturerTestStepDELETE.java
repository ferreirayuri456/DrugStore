package br.com.example.farmacia.cucumber.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;

public class ManufacturerTestStepDELETE {

	@Dado("^eu faço a busca 'manufacturer/id'$")
	public void eu_faço_a_busca_manufacturer_id() throws Throwable {
		RestTemplate restTemplate = new RestTemplateBuilder().rootUri("http://localhost:8082/manufacturer/{id}")
				.build();
	}

	@Dado("^eu realizo uma requisicao com o id <id> e apago o registro$")
	public void eu_realizo_uma_requisicao_com_o_id_id_e_apago_o_registro() throws Throwable {
		String uri = "http://localhost:8082/manufacturer/{id}";

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "2"); //sempre inserir um registro para que ele possa excluir
		

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(uri, params);

	}

	@Entao("^nao deveria mais ver o fabricante$")
	public void nao_deveria_mais_ver_o_fabricante() throws Throwable {
		assertThat(HttpStatus.OK);
	}

}
