package br.com.example.farmacia.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
features = "classpath:features", 
tags = "@FarmaciaTeste",  
glue = "br.com.example.farmacia.cucumber.controllerTest", 
monochrome = true, 
dryRun = false, 
strict = true)
public class TestProductCucumber {

}
