package br.com.example.farmacia.cucumber.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import br.com.example.farmacia.model.Product;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;

public class ProductTestStepPOST {

	@LocalServerPort
	private int port = 8082;
	private RestTemplate rest = new RestTemplate();
	private String postUrl = "http://localhost";
	
	private String id = "";

	@Dado("^eu posso criar um novo produto$")
	public void eu_posso_criar_um_novo_produto() throws Throwable {
		String url = postUrl + ":" + port + "/";
		List<Product> allProducts = rest.getForObject(url, List.class);

	}

	@Dado("^eu mando uma requisicao com o id(\\d+), codigo do produto (\\d+), o nome do produto \"([^\"]*)\", o nome fantasia \"([^\"]*)\", o fabricante \"([^\"]*)\" e o preco (\\d+)$")
	public void eu_mando_uma_requisicao_com_o_id_codigo_do_produto_o_nome_do_produto_o_nome_fantasia_o_fabricante_e_o_preco(
			int id, int codeProduct, String fantasyName, String nameProduct, String manufacturer, int price) throws Throwable {
		String url = postUrl + ":" + port + "/";
		Product newProd = new Product();
		newProd.setId(id);
		newProd.setCodeProduct(codeProduct);
		newProd.setFantasyName(fantasyName);
		newProd.setManufacturer(manufacturer);
		newProd.setNameProduct(nameProduct);
		newProd.setPrice(price);
		id = newProd.getId();
		Product[] prod = rest.getForObject(url, Product[].class);
		assertNotNull(prod);
	}

	@Entao("^eu deveria ver o novo produto criado$")
	public void eu_deveria_ver_o_novo_produto_criado() throws Throwable {
		String url = postUrl + ":" + port + "/" + id;
		Product[] prodCriado = rest.getForObject(url, Product[].class);
		assertNotNull(prodCriado);
		assertThat(HttpStatus.CREATED);
	}

}
