@PlanetandMoonVisibility
Feature: PlanetandMoonVisibility

	@PTA-TC-49 @JREQ-PTA-62
	Scenario Outline: As a user, I should not be able to view planets and moons without being logged-in, so that the Planetarium Home page content is visible by registered users only
	The system should check if the user is logged-in before showing planets and moons.
		Given The user is not logged-in
		When The User enters the "<URL>" for the Home page of Planetarium
		Then The user should be redirected to the Login page at "<URL>"

	Examples: 
		| URL                               |
		| http://localhost:8080/planetarium |
		| http://localhost:8080/login       |

	@PTA-TC-51 @JREQ-PTA-42
	Scenario Outline: As a user, I want to be able to view moons other users have added, so I can follow moons tracked by other users on the Planetarium
		Given The user is logged in
		When The user views the Planetarium Home page at "<URL>"
		Then The user should see the moon called "<Pre-existing moon>"
		And ID "<Moon ID>"
		And owner ID "<Planet Id>"

	Examples: 
		| Pre-existing moon | Planet Id | Moon ID | URL                               |
		| Luna              | 1         | 1       | http://localhost:8080/planetarium |

	@PTA-TC-53 @JREQ-PTA-41
	Scenario Outline: As a user, I want to be able to view moons I have added, so that I can track moons using the Planetarium
	The system should check if the user is logged-in.The system should check if the user is logged-in.
		Given The user is logged in
		And the user's name "<Existing Username>" is on the Planetarium Home page at "<URL>"
		Then The user should see the moon called "<Pre-existing moon>"
		And ID "<Moon ID>"
		And owner ID "<Planet Id>"

	Examples: 
		| Existing Username | URL                               | Pre-existing moon | Moon ID | Planet Id |
		| Batman            | http://localhost:8080/planetarium | Titan             | 2       | 2         |

	@PTA-TC-54 @JREQ-PTA-40
	Scenario Outline: As a user, I want to be able to view planets that other users have added, so that I can track the planets other users have added to the Planetarium
	The system checks if the user is logged-in.
		Given The user, "<Existing Username>" is logged into the Planetarium
		And another user, "<User ID>" added a planet to the Planetarium
		Then The logged-in user should see the planet, "<Pre-existing planet>"
		And planet ID "<Planet Id>"
		And owner ID "<User ID>"

	Examples: 
		| Existing Username | Pre-existing planet | Planet Id | User ID |
		| Joker             | Earth               | 1         | 1       |

	@PTA-TC-56 @JREQ-PTA-39
	Scenario Outline: As a user, I want to be able to view planets I have added, so I can track my planets in the Planetarium
	The system checks if the user is logged-in and that the user has added planets to the Planetarium.
		Given The user "<Existing Username>" is logged-in
		And The user has added planet "<Pre-existing planet>" to the Planetarium
		And The user has added planet "<Pre-existing planet>" to the Planetarium
		Then The user sees planet "<Pre-existing planet>" on the Planetarium Home page
		And The user sees planet "<Pre-existing planet>" on the Planetarium Home page

	Examples: 
		| Existing Username | Pre-existing planet |
		| Batman            | Earth               |
		|                   | Mars                |
		|                   | Earth               |
		|                   | Mars                |