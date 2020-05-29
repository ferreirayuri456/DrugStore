package br.com.example.farmacia.cucumber.steps;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.HashMap;
import java.util.Map;

import br.com.example.farmacia.util.RestAssuredExtension;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.it.Ma;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

public class ProductTestSteps {
	private ResponseOptions<Response> response;

	/****************************** GET BEGIN *****************************/
	@Given("^I perform GET operation for \"([^\"]*)\"$")
	public void i_perform_GET_operation_for(String url) throws Throwable {
	}

	@And("^I perform GET for the post number \"([^\"]*)\"$")
	public void i_perform_GET_for_the_post_number(String postNumber) throws Throwable {
		BDDMethodsProduct.GetPostFantasyName(postNumber);
	}

	@And("^I perform GET for the post name product \"([^\"]*)\"$")
	public void i_perform_GET_for_the_name_product(String postNumber) throws Throwable {
		BDDMethodsProduct.GetPostProductName(postNumber);
	}

	@And("^I perform GET for the post manufacturer \"([^\"]*)\"$")
	public void i_perform_GET_for_the_manufacturer(String postNumber) throws Throwable {
		BDDMethodsProduct.GetPostManufacturer(postNumber);
	}

	@And("^I perform GET for the post price \"([^\"]*)\"$")
	public void i_perform_GET_for_the_price(String postNumber) throws Throwable {
		BDDMethodsProduct.GetPostPrice(postNumber);
	}

	@Then("^I should see the fantasy name as \"([^\"]*)\"$")
	public void i_should_see_the_fantasy_name_as(String arg1) throws Throwable {
	}

	@Then("^I should see the code product as \"([^\"]*)\"$")
	public void i_should_see_the_code_product_as(String arg1) throws Throwable {

	}

	@Then("^I should see the manufacturer name as \"([^\"]*)\"$")
	public void i_should_see_the_manufacturer_name_as(String arg1) throws Throwable {

	}

	@Then("^I should see the price as \"([^\"]*)\"$")
	public void i_should_see_the_price_as(String arg1) throws Throwable {

	}

	@Then("^I should see the product name as \"([^\"]*)\"$")
	public void i_should_see_the_product_name_as(String arg1) throws Throwable {

	}

	/***************************************
	 * GET MANUFACTURER
	 ***************************************/

	@When("^I perform GET for the post number \"([^\"]*)\" for manufacturer$")
	public void i_perform_GET_for_the_post_number_for_manufacturer(String arg1) throws Throwable {
		BDDMethodsProduct.GetPostCodeManufacturer(arg1);
	}

	@Then("^I should see the code manufacturer as \"([^\"]*)\"$")
	public void i_should_see_the_code_manufacturer_as(String arg1) throws Throwable {

	}

	@When("^I perform GET for the post number \"([^\"]*)\" for manufacturer cnpf$")
	public void i_perform_GET_for_the_post_number_for_manufacturer_cnpf(String arg1) throws Throwable {
		BDDMethodsProduct.GetPostCNPJ(arg1);
	}

	@Then("^I should see the cnpj manufacturer as \"([^\"]*)\"$")
	public void i_should_see_the_cnpj_manufacturer_as(String arg1) throws Throwable {

	}

	@When("^I perform GET for the post number \"([^\"]*)\" for manufacturer country origin$")
	public void i_perform_GET_for_the_post_number_for_manufacturer_country_origin(String arg1) throws Throwable {
		BDDMethodsProduct.GetPostCountryOrigin(arg1);
	}

	@Then("^I should see the origin country manufacturer as \"([^\"]*)\"$")
	public void i_should_see_the_origin_country_manufacturer_as(String arg1) throws Throwable {

	}

	/******************************
	 * POST METHODS
	 ******************************/

	@Given("^I perform POST operation for \"([^\"]*)\"$")
	public void i_perform_POST_operation_for_products(String arg1) throws Throwable {
		BDDMethodsProduct.PostProducts();
	}

	@Given("^I perform POST operation for manufacturer \"([^\"]*)\"$")
	public void i_perform_POST_operation_for_manufacturer(String arg1) throws Throwable {
		BDDMethodsProduct.PostManufacturer();
	}

	/************************************
	 * DELETE METHODS
	 ************************************/

	@Given("^I ensure to perform POST operation for \"([^\"]*)\" with body as$")
	public void i_ensure_to_perform_POST_operation_for_with_body_as(String url, DataTable table) throws Throwable {
		BDDMethodsProduct.PostProducts();
	}

	@And("^I perform DELETE operation for \"([^\"]*)\"$")
	public void i_perform_DELETE_operation_for(String arg1) throws Throwable {

	}

	@Then("^I should not see the body with name product as \"([^\"]*)\"$")
	public void i_should_not_see_the_body_with_name_product_as(String codeProduct) throws Throwable {
	}

	@And("^I perform GET for the post number \"([^\"]*)\" for product$")
	public void i_perform_GET_for_the_post_number_for_product(String arg1) throws Throwable {
		
	}

}