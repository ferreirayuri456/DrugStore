package br.com.example.farmacia.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions
(features = "classpath:features", 
tags = "@FabricanteTeste", 
glue = "br.com.example.farmacia.cucumber.controllerTest", 
monochrome = true, 
dryRun = false,
strict = true)
public class TestManufacturerCucumber {

}
