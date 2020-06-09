Feature: DeletePosts 
	Test the delete operation

@smoke 
Scenario: Delete product 
	Given I perform GET operation for "/products" 
	And I perform GET for the post number "1" for product 
	And I perform DELETE operation for "/products/1" 
	Then I should not see the body with name product as "composto"