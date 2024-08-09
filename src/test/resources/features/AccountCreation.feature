@AccountCreation
Feature: AccountCreation

	@PTA-TC-1 @JREQ-PTA-9
	Scenario Outline: As a new user, I want to be able to create an account so that I can access the Planetarium
	Users should be able to input a username and password to create an account. The system should validate that the username is unique and that both username and password are under 30 characters.
		Given The user is on the Planetarium login page
		When The user clicks on the create an account button
		When The user enters "<Valid Username>" as a new username
		When The user enters "<Valid Password>" as a new password
		When The user clicks the Create button
		Then The user should be informed that the account creation was successful

	Examples: 
		| Valid Username                    | Valid Password                    |
		| !\"#$%&'()*+,-./:;<=>?@[]^_`\|\\~ | !\"#$%&'()*+,-./:;<=>?@[]^_`\|\\~ |
		| 000111222333444555666777888999    | 000111222333444555666777888999    |
		| Planets and Moons are awesomee    | Planets and Moons are awesomee    |

	@PTA-TC-2 @JREQ-PTA-33 @JREQ-PTA-8
	Scenario Outline: As a new user, I want to receive an error message if I attempt to create an account with a non-unique username, so that I am aware that the account creation failed
	The system should check if the username is already registered and provide a user-friendly error message if it is.
		Given The user is on the Planetarium login page
		When The user clicks on the create an account button
		When The user enters "<Username>" as a new username
		When The user enters "<Password>" as a new password
		When The user clicks the Create button
		Then The user should be informed that the account creation failed

	Examples: 
		| Username | Password                       |
		| Batman   | Planets and Moons are awesomee |

	@PTA-TC-42 @JREQ-PTA-31
	Scenario Outline: As a new user, I want to receive an error message if I attempt to create an account with a too long username, so that I am aware that the account creation failed
	The system should check if the username is longer than 30 characters and provide a user-friendly error message if it is.
		Given The user is on the Planetarium login page
		When The user clicks on the create an account button
		When The user enters "<Username>" as a new username
		When The user enters "<Password>" as a new password
		When The user clicks the Create button
		Then The user should be informed that the account creation failed

	Examples: 
		| Username                        | Password                       |
		| Planets and Moons are fantastic | Planets and Moons are awesomee |

	@PTA-TC-43
	Scenario Outline: As a new user, I want to receive an error message if I attempt to create an account with a blank username, so that I am aware that the account creation failed
	The system should check if the username is blank and provide a user-friendly error message if it is.
		Given The user is on the Planetarium login page
		When The user clicks on the create an account button
		When The user enters "<Username>" as a new username
		When The user enters "<Password>" as a new password
		When The user clicks the Create button
		Then The user should be informed that the account creation failed

	Examples: 
		| Username | Password                       |
		|          | Planets and Moons are awesomee |

	@PTA-TC-44 @JREQ-PTA-32
	Scenario Outline: As a new user, I want to receive an error message if I attempt to create an account with a too long password, so that I am aware that the account creation failed
	The system should check if the password is longer than 30 characters and provide a user-friendly error message if it is.
		Given The user is on the Planetarium login page
		When The user clicks on the create an account button
		When The user enters "<Username>" as a new username
		When The user enters "<Password>" as a new password
		When The user clicks the Create button
		Then The user should be informed that the account creation failed

	Examples: 
		| Username             | Password                        |
		| ThisIsAValidUsername | Planetarium is very interesting |

	@PTA-TC-45
	Scenario Outline: As a new user, I want to receive an error message if I attempt to create an account with a blank password, so that I am aware that the account creation failed
	The system should check if the password is blank and provide a user-friendly error message if it is.
		Given The user is on the Planetarium login page
		When The user clicks on the create an account button
		When The user enters "<Username>" as a new username
		When The user enters "<Password>" as a new password
		When The user clicks the Create button
		Then The user should be informed that the account creation failed

	Examples: 
		| Username             | Password |
		| ThisIsAValidUsername |          |