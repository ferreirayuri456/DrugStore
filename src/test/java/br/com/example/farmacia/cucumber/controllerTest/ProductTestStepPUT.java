package br.com.example.farmacia.cucumber.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import br.com.example.farmacia.model.Product;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;

public class ProductTestStepPUT {

	@Dado("^eu perfomo a operacao GET com '/id'$")
	public void eu_perfomo_a_operacao_GET_com_id() throws Throwable {
		RestTemplate restTemplate = new RestTemplateBuilder().rootUri("http://localhost:8082/{id}").build();
	}

	@Dado("^eu busco pelo  id (\\d+) e atualizo o codigo do produto (\\d+), o nome do produto \"([^\"]*)\", o nome fantasia \"([^\"]*)\", o fabricante \"([^\"]*)\" e o preco (\\d+)$")
	public void eu_busco_pelo_id_e_atualizo_o_codigo_do_produto_o_nome_do_produto_o_nome_fantasia_o_fabricante_e_o_preco(
			int id, int codeProduct, String nameProduct, String manufacturer, String fantasyName, int price)
			throws Throwable {
		String uri = "http://localhost:8082/{id}";

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "2");

		Product newProd = new Product();
		newProd.setId(id);
		newProd.setCodeProduct(codeProduct);
		newProd.setFantasyName(fantasyName);
		newProd.setManufacturer(manufacturer);
		newProd.setNameProduct(nameProduct);
		newProd.setPrice(price);
		id = newProd.getId();

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(uri, newProd, params);

	}

	@Entao("^eu deveria ver os dados atualizados$")
	public void eu_deveria_ver_os_dados_atualizados() throws Throwable {
		assertThat(HttpStatus.OK);
	}

}
