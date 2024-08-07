@Login
Feature: Login

	@PTA-TC-46 @JREQ-PTA-17
	Scenario Outline: As a user, I want to be able to securely login so I can access my planetarium account
	Users should be able to input a valid username and password to securely access their planetarium account
		Given The user is on the Planetarium login page
		When The user enters "<Username>" as the username
		When The user enters "<Password>" as the password
		When The user clicks on the Login button
		Then The user should be redirected to the Login page

	Examples: 
		| Username | Password       |
		| Batman   | I am the night |

	@PTA-TC-47 @JREQ-PTA-18
	Scenario Outline: As a user, I want to receive an error message if I attempt to login with invalid credentials, so I am aware that the account login failed
	The system should check if the username and password combination is invalid and provide a user-friendly error message if it is.
		Given The user is on the Planetarium login page
		When The user enters "<Username>" as the username
		When The user enters "<Password>" as the password
		When The user clicks on the Login button
		Then The user should be informed that the account login failed

	Examples: 
		| Username                        | Password                       |
		| Batman                          | Planets and Moons are awesomee |
		|                                 | Planets and Moons are awesomee |
		| Planets and Moons are fantastic | Planets and Moons are awesomee |
		| Batman                          |                                |