package br.com.example.farmacia.cucumber.stepDefinitions;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.example.farmacia.model.Product;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import io.cucumber.java.pt.Entao;

public class ProductTestStep {

	final String BASE_PATH = "http://localhost:8082/";

	@Autowired
	RestTemplate restTemplate;

	private ObjectMapper mapper = new ObjectMapper();

	@Dado("^eu perfomo a operacao GET com '/'$")
	public void eu_perfomo_a_operacao_GET_com() throws Throwable {
		String response = restTemplate.getForObject(BASE_PATH, String.class);

		List<Product> produtos = mapper.readValue(response,
				mapper.getTypeFactory().constructCollectionType(List.class, Product.class));
		Product prod = restTemplate.getForObject(BASE_PATH + produtos.get(1).getId(), Product.class);
		
		Assert.assertNotNull(prod);
		Assert.assertEquals("1", prod.getCodeProduct());
	}

	@E("^eu perfomo a operacao GET pelo numero '(\\d+)'$")
	public void eu_perfomo_a_operacao_GET_pelo_numero(int arg1) throws Throwable {
	
	}

	@Entao("^eu deveria ver o nome fantasia igual a \"([^\"]*)\"$")
	public void eu_deveria_ver_o_nome_fantasia_igual_a(String arg1) throws Throwable {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8082/";
		Product prod = restTemplate.getForObject(url + "1", Product.class);
		assertThat(prod.getFantasyName(), notNullValue());
		assertThat(prod.getFantasyName(), equalTo("DOVE"));
	}

	@Entao("^eu deveria ver o codigo do produto igual a \"([^\"]*)\"$")
	public void eu_deveria_ver_o_codigo_do_produto_igual_a(String arg1) throws Throwable {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8082/";
		Product prod = restTemplate.getForObject(url + "1", Product.class);
		assertThat(prod.getCodeProduct(), notNullValue());
		assertThat(prod.getCodeProduct(), equalTo("1"));
	}

	@Entao("^eu deveria ver o nome do produto igual a \"([^\"]*)\"$")
	public void eu_deveria_ver_o_nome_do_produto_igual_a(String arg1) throws Throwable {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8082/";
		Product prod = restTemplate.getForObject(url + "1", Product.class);
		assertThat(prod.getNameProduct(), notNullValue());
		assertThat(prod.getNameProduct(), equalTo("DOVE"));
	}

}
