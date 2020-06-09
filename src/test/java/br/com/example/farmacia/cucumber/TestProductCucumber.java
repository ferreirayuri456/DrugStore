package br.com.example.farmacia.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", tags = "@ProdutoTeste", glue = "br.com.example.farmacia.cucumber.stepDefinitions", monochrome = true, dryRun = false, strict = true)
public class TestProductCucumber {

}
