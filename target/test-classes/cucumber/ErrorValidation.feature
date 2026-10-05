Feature: Error Validation

@ErrorValidation
Scenario Outline: Error Validation for Login Page

Given I landed on the Website
When I logged in with <username> and <password>
Then "Incorrect email password." message should be displayed

Examples:
	| username    			| password     |
	| shruti16@gmail.com    | Shruti123    |