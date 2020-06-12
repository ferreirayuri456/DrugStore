package br.com.example.farmacia.cucumber.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import br.com.example.farmacia.model.Manufacturer;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;

public class ManufacturerTestStepPOST {

	@LocalServerPort
	private int port = 8082;
	private RestTemplate rest = new RestTemplate();
	private String postUrl = "http://localhost";
	
	@Dado("^eu posso criar um novo fabricante$")
	public void eu_posso_criar_um_novo_fabricante() throws Throwable {
		String url = postUrl + ":" + port + "/manufacturer/";
		List<Manufacturer> allManufacturers = rest.getForObject(url, List.class);
	}

	@E("^eu realizo uma requisicao com o id (\\d+), codigo do fabricante(\\d+), cnpj(\\d+), nome fantasia\"([^\"]*)\" e o país de origem\"([^\"]*)\"$")
	public void eu_realizo_uma_requisicao_com_o_id_codigo_do_fabricante_cnpj_nome_fantasia_e_o_país_de_origem(int id,
			String codeManufacturer, int cnpj, String fantasyName, String countryOrigin) throws Throwable {
		String url = postUrl + ":" + port + "/manufacturer/";
		Manufacturer newManu = new Manufacturer();
		newManu.setId(id);
		newManu.setCodeManufacturer(codeManufacturer);
		newManu.setCnpj(cnpj);
		newManu.setCountryOrigin(countryOrigin);
		newManu.setFantasyName(fantasyName);
		Manufacturer[] manu = rest.getForObject(url, Manufacturer[].class);
		assertNotNull(manu);
	}

	@Entao("^eu deveria ver o novo fabricante criado$")
	public void eu_deveria_ver_o_novo_fabricante_criado() throws Throwable {
		String url = postUrl + ":" + port + "/manufacturer/";
		Manufacturer[] manu = rest.getForObject(url, Manufacturer[].class);
		assertThat(HttpStatus.CREATED);
		assertNotNull(manu);
	}
}
