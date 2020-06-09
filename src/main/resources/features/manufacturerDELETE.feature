Feature: DeletePosts 
	Test the delete operation
	
Scenario: Delete manufacturer 
	Given I perform GET operation for "/manufacturer" 
	And I perform GET for the post number "1" for manufacturer 
	And I perform DELETE operation for "/manufacturer/1" 
	Then I should not see the body with name product as "composto"