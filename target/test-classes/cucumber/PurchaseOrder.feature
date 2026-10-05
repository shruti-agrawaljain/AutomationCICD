@tag
Feature: Purchase the Order from E-Commerce Website

	Background: 
	Given I landed on the Website

	
	@Regression
	Scenario Outline: Submitting the order successfully
	Given I logged in with <username> and <password>
	When I want to add product <productName> to Cart
	And Checkout <productName> and submit the order
	Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation Page
	
	Examples:
	|username     			| password      |  productName 	|
	|shruti16@gmail.com  	| Shruti@123    |  ZARA COAT 3 	|


