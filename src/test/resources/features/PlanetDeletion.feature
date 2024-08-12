@PlanetDeletion
Feature: PlanetDeletion

	@PTA-TC-71 @JREQ-PTA-68
	Scenario Outline: As a user, I should be able to delete a planet I added without an associated moon using a valid id, so that my planetarium is up to date
	System should check that the given planet id is the primary key for an existing planet created by the user with no associated moons, and remove that planet from the planetarium if so.
		Given The user is logged in
		When The user sets the dropdown to display the planet creation menu
		When The user enters "<Planet Id>" as the celestial body to be deleted
		When The user presses the delete button
		Then The planet with the id "<Planet Id>" should be deleted from the Planetarium

	Examples:
		| Planet Id |
		| 3         |

	@PTA-TC-74 @JREQ-PTA-51
	Scenario Outline: As a user, I want to be able to remove planets from the planetarium by their name so that we can stop tracking them
	This is to verify that we can remove planets if we pass in their name, spelled correctly and with the correct case. And that the user will see that the planet and it's orbiting moon is no longer within the planetarium
This will also act as a test to
		Given The user is logged in
		When The user sets the dropdown to display the planet creation menu
		When The user enters "<Pre-existing planet>" as the celestial body to be deleted
		When The user presses the delete button
		Then The user should no longer see the planet "<Pre-existing planet>" within the planetarium

	Examples:
		| Pre-existing planet    |
		| Earth                  |
		| Planet .^$*+-?()[]{}// |

	@PTA-TC-78 @JREQ-PTA-74
	Scenario Outline: As a user, I want to receive an error when I attempt to delete a planet using an ID that does not point to a planet, so that I know the planet was not deleted
	We are attempting to verify that we cannot delete a planet if we use an invalid ID as input, and that the user receives an error message as a response to this invalid data.
		Given The user is logged in
		And The user sets the dropdown to display the planet creation menu
		When The user enters "<Invalid foreign key>" as the celestial body to be deleted
		When The user presses the delete button
		Then The user should be informed that the planet deletion failed

	Examples:
		| Invalid foreign key |
		| 0                   |
		| 1000                |
		| 1000000             |

	@PTA-TC-79 @JREQ-PTA-71
	Scenario Outline: As a user, I want to receive an error if I attempt to delete a planet while using the name of a moon, so that I know the planet deletion failed
	We are attempting to verify that the user cannot delete a planet if they are using an existing moon name, and that they receive an error message as a response to this misuse of data.
		Given The user is logged in
		And The user sets the dropdown to display the planet creation menu
		When The user enters "<Pre-existing moon>" as the celestial body to be deleted
		When The user presses the delete button
		Then The user should be informed that the planet deletion failed

	Examples:
		| Pre-existing moon |
		| Titan             |
		| Luna              |

	@PTA-TC-80 @JREQ-PTA-69
	Scenario: As a user, I want to receive an error if I attempt to delete a planet without inputting a name, so that I know planet deletion failed.
	This is to verify that no planet is deleted if the user inputs no characters for a planet name, and that they receive an error message to inform them planet deletion failed.
		Given The user is logged in
		And The user sets the dropdown to display the planet creation menu
		When The user enters nothing as the celestial body to be deleted
		When The user presses the delete button
		Then The user should be informed that the planet deletion failed

	@PTA-TC-81 @JREQ-PTA-70
	Scenario Outline: As a User, I want to receive an error if I input the name for a planet that does not exist, so that I know planet deletion failed
	This is to verify that no planet is deleted if the user inputs a name that does not point to any existing planet, and that receive an error message to inform them planet deletion failed
		Given The user is logged in
		And The user sets the dropdown to display the planet creation menu
		When The user enters "<Non-existent planet>" as the celestial body to be deleted
		When The user presses the delete button
		Then The user should be informed that the planet deletion failed

	Examples:
		| Non-existent planet     |
		| This planet is not real |
		| Neither is this one     |

	@PTA-TC-82 @JREQ-PTA-77
	Scenario Outline: As a user, I want to be able to see that once I remove a planet from the planetarium, that it's orbiting moons are removed as well, so that we are no longer tracking them
	This is to verify that moons are not left orphaned when we are removing their parent planet.
		Given The user is logged in
		And The user sets the dropdown to display the planet creation menu
		When The user enters "<Pre-existing planet>" as the celestial body to be deleted
		When The user presses the delete button
		Then The user should no longer see "<Pre-existing planet>" and "<Pre-existing moon>" within the planetarium

	Examples:
		| Pre-existing planet | Pre-existing moon |
		| Earth               | Luna              |

	@PTA-TC-83 @JREQ-PTA-73
	Scenario Outline: As a user, I want to be able to remove planets from the planetarium which contain numbers within their name, so that we can stop tracking them
	This is to verify that we can remove planets who's names are either partly or fully made up of numbers
		Given The user is logged in
		And The user sets the dropdown to display the planet creation menu
		When The user enters "<Planet number names>" as the celestial body to be deleted
		When The user presses the delete button
		Then The user should no longer see the planet "<Planet number names>" within the planetarium

	Examples:
		| Planet number names |
		| 2                   |
		| Planet123           |
