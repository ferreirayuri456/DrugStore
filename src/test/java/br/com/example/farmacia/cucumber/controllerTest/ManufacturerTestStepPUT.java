package br.com.example.farmacia.cucumber.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import br.com.example.farmacia.model.Manufacturer;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;

public class ManufacturerTestStepPUT {

	@Dado("^eu perfomo a operacao GET com 'manufacturer/id'$")
	public void eu_perfomo_a_operacao_GET_com_manufacturer_id() throws Throwable {
		RestTemplate restTemplate = new RestTemplateBuilder().rootUri("http://localhost:8082/manufacturer/{id}")
				.build();
	}

	@Dado("^eu realizo uma requisicao com o id (\\d+), codigo do fabricante\"([^\"]*)\", cnpj(\\d+), nome fantasia\"([^\"]*)\" e o país de origem\"([^\"]*)\"$")
	public void eu_realizo_uma_requisicao_com_o_id_codigo_do_fabricante_cnpj_nome_fantasia_e_o_país_de_origem(int id,
			String codeManufacturer, int cnpj, String countryOrigin, String fantasyName) throws Throwable {
		String uri = "http://localhost:8082/manufacturer/{id}";

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "3");

		Manufacturer newManu = new Manufacturer();
		newManu.setId(id);
		newManu.setCnpj(cnpj);
		newManu.setCodeManufacturer(codeManufacturer);
		newManu.setCountryOrigin(countryOrigin);
		newManu.setFantasyName(fantasyName);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(uri, newManu, params);
	}

	@Entao("^eu deveria ver os dados do fabricante atualizados$")
	public void eu_deveria_ver_os_dados_do_fabricante_atualizados() throws Throwable {
		assertThat(HttpStatus.OK);
	}
}
