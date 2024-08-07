@PlanetAddition
Feature: PlanetAddition

	@PTA-TC-58 @JREQ-PTA-50
	Scenario Outline: As a user, I want to receive an error message if I attempt to add a planet with an already existing planet name, so that I am aware that the planet addition failed
	The system should check if the name already exists for a planet and provide a user-friendly error message if it is.
		Given The user is logged in
		When The user selects the planet option from the dropdown
		When The user enters "<New planet names>" as the planet name
		When The user uploads "<Celestial body file name>" as the image
		When The user clicks on the Submit Planet button
		Then The user should be informed that the planet addition failed

	Examples: 
		| New planet names | Celestial body file name |
		| Earth            | planet-1.jpg             |

	@PTA-TC-62 @JREQ-PTA-66
	Scenario Outline: As a user, I want to receive an error message if I attempt to add a planet with an already existing moon name, so that I am aware that the planet addition failed
	The system should check if the name already exists for a moon and provide a user-friendly error message if it is.
		Given The user is logged in
		When The user selects the planet option from the dropdown
		When The user enters "<New planet names>" as the planet name
		When The user uploads "<Celestial body file name>" as the image
		When The user clicks on the Submit Planet button
		Then The user should be informed that the planet addition failed

	Examples: 
		| New planet names | Celestial body file name |
		| Luna             | planet-1.jpg             |

	@PTA-TC-63 @JREQ-PTA-49
	Scenario Outline: As a user, I want to receive an error message if I attempt to add a planet with a too long planet name, so that I am aware that the planet addition failed
	The system should check if the  planet is longer than 30 characters and provide a user-friendly error message if it is.
		Given The user is logged in
		When The user selects the planet option from the dropdown
		When The user enters "<New planet names>" as the planet name
		When The user uploads "<Celestial body file name>" as the image
		When The user clicks on the Submit Planet button
		Then The user should be informed that the planet addition failed

	Examples: 
		| New planet names                | Celestial body file name |
		| ThePlanetNameIsTooLongForAdding | planet-1.jpg             |

	@PTA-TC-64 @JREQ-PTA-67
	Scenario Outline: As a user, I want to receive an error message if I attempt to add a planet with a blank planet name, so that I am aware that the planet addition failed
	The system should check if the planet name was left blank and provide a user-friendly error message if it is.
		Given The user is logged in
		When The user selects the planet option from the dropdown
		When The user enters "<New planet names>" as the planet name
		When The user uploads "<Celestial body file name>" as the image
		When The user clicks on the Submit Planet button
		Then The user should be informed that the planet addition failed

	Examples: 
		| New planet names | Celestial body file name |
		|                  | planet-1.jpg             |

	@PTA-TC-66 @JREQ-PTA-47
	Scenario Outline: As a user, I should be able to add planets with a valid planet name and image, so it will visible in the Planetarium
	Users should be able to input a valid planet name and image to add a planet to the planetarium
		Given The user is logged in
		When The user selects the planet option from the dropdown
		When The user enters "<New planet names>" as the planet name
		When The user uploads "<Celestial body file name>" as the image
		When The user clicks on the Submit Planet button
		Then The planet "<New planet names>" should be added to the Planetarium

	Examples: 
		| New planet names               | Celestial body file name |
		| This is a newly made up planet | planet-1.jpg             |
		| Planet .^$*+-?()[]{}\|         | planet-1.jpg             |
		| 1                              | planet-1.jpg             |

	@PTA-TC-67 @JREQ-PTA-46
	Scenario Outline: As a user, I should be able to add planets with a valid planet name without an image, so it will visible in the Planetarium
	Users should be able to input a valid planet name without an image to add a planet to the planetarium
		Given The user is logged in
		When The user selects the planet option from the dropdown
		When The user enters "<New planet names>" as the planet name
		When The user clicks on the Submit Planet button
		Then The planet "<New planet names>" should be added to the Planetarium

	Examples: 
		| New planet names               |
		| You can also count this planet |
