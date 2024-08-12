@PlanetandMoonVisibility
Feature: PlanetandMoonVisibility

	@PTA-TC-49 @JREQ-PTA-62
	Scenario: As a user, I should not be able to view planets and moons without being logged-in, so that the Planetarium Home page content is visible by registered users only
	The system should check if the user is logged-in before showing planets and moons.
		Given The user is on the Planetarium login page
		When The user directly goes to the Home page of Planetarium "<URL>"
		Then The user should be informed they need to login first

	Examples: 
		| URL                               |
		| http://localhost:8080/planetarium |

	@PTA-TC-51 @JREQ-PTA-42
	Scenario Outline: As a user, I want to be able to view moons other users have added, so I can follow moons tracked by other users on the Planetarium
		Given The user is logged in
		Then The user should see the moon called "<Pre-existing moon>", moon ID "<Moon ID>", and owner ID "<Planet Id>"

	Examples: 
		| Pre-existing moon | Planet Id | Moon ID | URL                               |
		| Luna              | 1         | 1       | http://localhost:8080/planetarium |

	@PTA-TC-53 @JREQ-PTA-41
	Scenario Outline: As a user, I want to be able to view moons I have added, so that I can track moons using the Planetarium
	The system should check if the user is logged-in.The system should check if the user is logged-in.
		Given The user is logged in
		Then The user should see the moon called "<Pre-existing moon>", moon ID "<Moon ID>", and owner ID "<Planet Id>"

	Examples: 
		| Pre-existing moon | Moon ID | Planet Id |
		| Titan             | 2       | 2         |

	@PTA-TC-54 @JREQ-PTA-40
	Scenario Outline: As a user, I want to be able to view planets that other users have added, so that I can track the planets other users have added to the Planetarium
	The system checks if the user is logged-in.
		Given The user is logged in
		Then The logged in user should see the planet, "<Pre-existing planet>", planet ID "<Planet Id>", and owner ID "<User ID>"

	Examples: 
		| Pre-existing planet | Planet Id | User ID |
		| Jupiter             | 3         | 2       |

	@PTA-TC-56 @JREQ-PTA-39
	Scenario Outline: As a user, I want to be able to view planets I have added, so I can track my planets in the Planetarium
	The system checks if the user is logged-in and that the user has added planets to the Planetarium.
		Given The user is logged in
		Then The planet "<Pre-existing planet>" should be added to the Planetarium

	Examples: 
		| Pre-existing planet |
		| Earth               |