package br.com.example.farmacia.cucumber.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.example.farmacia.model.Product;
import cucumber.api.java.pt.Dado;
import io.cucumber.java.pt.Entao;

public class ProductTestStepGET {

	@LocalServerPort
	private int port = 8082;
	private RestTemplate rest = new RestTemplate();
	private String postUrl = "http://localhost";

	@Autowired
	RestTemplate restTemplate;

	@Dado("^eu perfomo a operacao GET com '/'$")
	public void eu_perfomo_a_operacao_GET_com() throws Throwable {
		String url = postUrl + ":" + port + "/";
		ResponseEntity<String> response = rest.getForEntity(url, String.class);
	}

	@Dado("^eu perfomo a operacao GET pelo numero '(\\d+)'$")
	public void eu_perfomo_a_operacao_GET_pelo_numero(int arg1) throws Throwable {
		final String uri = "http://localhost:8082/";

		Map<String, String> params = new HashMap<String, String>();
	    params.put("id", "1");
	     
	    RestTemplate restTemplate = new RestTemplate();
	    Product[] result = restTemplate.getForObject(uri, Product[].class, params);
	}

	@Entao("^eu deveria ver os produtos$")
	public void eu_deveria_ver_os_produtos() throws Throwable {
	    assertThat(HttpStatus.OK);
	}


}