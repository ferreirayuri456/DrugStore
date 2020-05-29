package br.com.example.farmacia.cucumber.steps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.HashMap;
import java.util.Random;

import org.hamcrest.core.Is;

import io.restassured.http.ContentType;

public class BDDMethodsProduct {

	public static void GetPostFantasyName(String postNumber) {
		given().contentType(ContentType.JSON)
			.when().get(String.format("http://localhost:3000/products/%s", postNumber))
				.then().body("fantasyName", is("Dove"));
	}
	
	public static void GetPostCodeProduct(String postNumber) {
		given().contentType(ContentType.JSON)
			.when().get(String.format("http://localhost:3000/products/%s", postNumber))
				.then().body("codeProduct", is(1));
	}
	
	public static void GetPostProductName(String postNumber) {
		given().contentType(ContentType.JSON)
			.when().get(String.format("http://localhost:3000/products/%s", postNumber))
				.then().body("nameProduct", is("Dove"));
	}
	
	public static void GetPostManufacturer(String postNumber) {
		given().contentType(ContentType.JSON)
			.when().get(String.format("http://localhost:3000/products/%s", postNumber))
				.then().body("manufacturer", is("DOVE"));
	}
	
	public static void GetPostPrice(String postNumber) {
		given().contentType(ContentType.JSON)
			.when().get(String.format("http://localhost:3000/products/%s", postNumber))
				.then().body("price", is(123));
	}
	
	public static void PostProducts() {
		HashMap<String , String> postContent = new HashMap<>();
		postContent.put("id", "189");
		postContent.put("codeProduct", "000");
		postContent.put("nameProduct", "Desodorante Feminino");
		postContent.put("fantasyName", "Spray para axilas");
		postContent.put("manufacturer", "Dove");
		postContent.put("price", "13");
		
		given()
				.contentType(ContentType.JSON).
		with()
				.body(postContent).
		when()
				.post("http://localhost:3000/products").
		then()
				.body("codeProduct", Is.is("000"));
				
	}
	
	/****************************************MANUFACTURER********************************************/
	public static void GetPostCodeManufacturer(String postNumber) {
		given().contentType(ContentType.JSON)
			.when().get(String.format("http://localhost:3000/manufacturer/%s", postNumber))
				.then().body("codeManufacturer", is("1596753"));
	}
	
	public static void GetPostCNPJ(String postNumber) {
		given().contentType(ContentType.JSON)
			.when().get(String.format("http://localhost:3000/manufacturer/%s", postNumber))
				.then().body("cnpj", is(15975366));
	}
	
	public static void GetPostCountryOrigin(String postNumber) {
		given().contentType(ContentType.JSON)
			.when().get(String.format("http://localhost:3000/manufacturer/%s", postNumber))
				.then().body("countryOrigin", is("Brazil"));
	}
	
	public static void GetPostFantasyNameManufacturer(String postNumber) {
		given().contentType(ContentType.JSON)
			.when().get(String.format("http://localhost:3000/manufacturer/%s", postNumber))
				.then().body("fantasyName", is("Dove"));
	}
	
	public static void PostManufacturer() {
		HashMap<String , String> postContent = new HashMap<>();
		postContent.put("id", "9");
		postContent.put("codeManufacturer", "15958");
		postContent.put("fantasyName", "Needs");
		postContent.put("cnpj", "156896");
		postContent.put("countryOrigin", "Brazil");
		
		given()
				.contentType(ContentType.JSON).
		with()
				.body(postContent).
		when()
				.post("http://localhost:3000/manufacturer").
		then()
				.body("codeManufacturer", Is.is("15958"));
				
	}
	
	
	
	
}
