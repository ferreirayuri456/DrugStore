@ProductTest 
Feature: Test CRUD methods in testing the product registrations REST API 

Scenario: List manufacturer for fantasy name 
	Given I perform GET operation for "/manufacturer" 
	And I perform GET for the post number "1" 
	Then I should see the fantasy name as "Dove" 
	
Scenario: List manufacturer for code manufacturer 
	Given I perform GET operation for "/manufacturer" 
	When I perform GET for the post number "1" for manufacturer 
	Then I should see the code manufacturer as "1596753" 
	
Scenario: List manufacturer for cnpj 
	Given I perform GET operation for "/manufacturer" 
	When I perform GET for the post number "1" for manufacturer cnpf 
	Then I should see the cnpj manufacturer as "15975366" 
	
Scenario: List manufacturer for country origin 
	Given I perform GET operation for "/manufacturer" 
	When I perform GET for the post number "1" for manufacturer country origin 
	Then I should see the origin country manufacturer as "Brazil" 
	