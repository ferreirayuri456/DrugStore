@ProductTest 
Feature: Test CRUD methods in testing the product registrations REST API 

Scenario: List product for fantasy name 
	Given I perform GET operation for "/products" 
	And I perform GET for the post number "1" 
	Then I should see the fantasy name as "Dove" 
	
Scenario: List product for code product 
	Given I perform GET operation for "/products" 
	And I perform GET for the post number "1" 
	Then I should see the code product as "1" 
	
Scenario: List product for product name 
	Given I perform GET operation for "/products" 
	And I perform GET for the post number "1" 
	Then I should see the product name as "Dove" 
	
Scenario: List product for manufacturer 
	Given I perform GET operation for "/products" 
	And I perform GET for the post number "1" 
	Then I should see the manufacturer name as "DOVE" 
	
Scenario: List product for price 
	Given I perform GET operation for "/products" 
	And I perform GET for the post number "1" 
	Then I should see the price as "123" 

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
	
Scenario: Verify POST operation 
	Given I perform POST operation for manufacturer "/manufacturer" 
	
Scenario: Verify POST operation 
	Given I perform POST operation for "/products" 
	
Scenario: Delete product 
	Given I perform GET operation for "/products"
	And I perform GET for the post number "1" for product  
	And I perform DELETE operation for "/products/1" 
	Then I should not see the body with name product as "composto"
	
Scenario: Delete manufacturer 
	Given I perform GET operation for "/manufacturer"
	And I perform GET for the post number "1" for manufacturer  
	And I perform DELETE operation for "/manufacturer/1" 
	Then I should not see the body with name product as "composto"
		
	