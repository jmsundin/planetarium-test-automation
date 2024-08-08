@MoonAddition
Feature: MoonAddition

	@PTA-TC-55 @JREQ-PTA-35
	Scenario Outline: As a User, I want to add a Moon by providing a valid name, valid planet ID, and valid picture so that this moon is displayed within the planetarium
	This is to verify that a moon can be created if the application is provided with valid details while including a picture for the moon
		Given The user is logged in
		And The user sets the dropdown to display the moon creation menu
		When The user enters "<New moon name>" as the Moon Name
		When The user enters "<Planet foreign key>" as the Orbited Planet ID
		When The user uploads "<Celestial body file name>" as Image
		When The user clicks on the Submit Moon button
		Then The moon should be added to the planetarium

	Examples: 
		| New moon name                  | Planet foreign key | Celestial body file name |
		| ThisIsTheSupererestMoonName!!! | 2                  | moon-1.jpg               |
		| A                              | 1                  | moon-1.jpg               |

	@PTA-TC-61 @JREQ-PTA-27
	Scenario Outline: As a user, I want to add a Moon by providing a valid moon name and a planet ID so that this moon is displayed within the planetarium
	This is to verify that a moon can be created if the application is provided with valid details while excluding a picture for the moon
		Given The user is logged in
		And The user sets the dropdown to display the moon creation menu
		When The user enters "<New moon name>" as the Moon Name
		When The user enters "<Planet foreign key>" as the Orbited Planet ID
		When The user clicks on the Submit Moon button
		Then The moon should be added to the planetarium

	Examples: 
		| New moon name                  | Planet foreign key |
		| ThisIsTheSupererestMoonName!!! | 2                  |
		| A                              | 1                  |

	@PTA-TC-65 @JREQ-PTA-65
	Scenario Outline: As a user, I want to receive an error when I attempt to use an already existing planet name while attempting to create a moon so that I know that the moon was not created
	This is to verify that the user cannot use the name of a existent planet to create a moon, and that the user receives an error message 
		Given The user is logged in
		And The user sets the dropdown to display the moon creation menu
		When The user enters "<Pre-existing planet>"
		When The user enters "<Planet foreign key>" as the Orbited Planet ID
		When The user uploads "<Celestial body file name>" as Image
		When The user clicks on the Submit Moon button
		Then The user should be informed that the moon addition failed

	Examples: 
		| Pre-existing planet |
		| Mars                |
		| Earth               |

	@PTA-TC-68 @JREQ-PTA-48
	Scenario Outline: As a user, I want to receive an error when I attempt to use an already existing moon name while attempting to create a moon so that I know that the moon was not created
	This is to verify that a user cannot create a multiple moons with the same name, and that the user gets an error when they attempt this.
		Given The user is logged in
		And The user sets the dropdown to display the moon creation menu
		When The user enters "<Pre-existing moon>" as the Moon Name
		When The user enters "<Planet foreign key>" as the Orbited Planet ID
		When The user uploads "<Celestial body file name>" as Image
		When The user clicks on the Submit Moon button
		Then The user should be informed that the moon creation failed

	Examples: 
		| Pre-existing moon | Planet foreign key | Celestial body file name |
		| Titan             | 2                  | moon-1.jpg               |
		| Luna              | 1                  | moon-1.jpg               |

	@PTA-TC-69 @JREQ-PTA-45
	Scenario Outline: As a user, I want to receive an error message when I attempt to create a moon using a planet ID that does not point to a real planet, so that I know the moon creation failed
	We are attempting to verify that we cannot create moons while providing a planet ID for a planet that does not exist, and that the user receives an error message as a response to this invalid data.
		Given The user is logged in
		And The user sets the dropdown to display the moon creation menu
		When The user enters "<New moon name>" as the Moon Name
		When The user enters "<Invalid foreign key>" as the Orbited Planet ID
		When The user uploads "<Celestial body file name>" as Image
		When The user clicks on the Submit Moon button
		Then The user should be informed that the moon addition failed

	Examples: 
		| New moon name                  | Invalid foreign key | Celestial body file name |
		| ThisIsTheSupererestMoonName!!! | 0                   | moon-1.jpg               |
		| A                              | 1000000             | moon-1.jpg               |
		| ThisIsTheSupererestMoonName!!! |                     | moon-1.jpg               |

	@PTA-TC-70 @JREQ-PTA-28
	Scenario Outline: As a user, I want to receive an error when I do not include a moon name when attempting to create a moon so that I know mood addition failed
	We are attempting to verify that we cannot create moons without providing a name for the moon, and that the user receives an error message as a response to this invalid data.
		Given The user is logged in
		And The user sets the dropdown to display the moon creation menu
		When The user enters "<Planet foreign key>" as the Orbited Planet ID
		When The user uploads "<Celestial body file name>" as Image
		When The user clicks on the Submit Moon button
		Then The user should be informed that the moon addition failed

	Examples: 
		| Planet foreign key | Celestial body file name |
		| 1                  | moon-1.jpg               |
		| 2                  | moon-1.jpg               |

	@PTA-TC-72 @JREQ-PTA-37
	Scenario Outline: As a user, I want to receive an error message if I attempt to add a moon within a name that is longer than thirty characters long, so that I know moon addition failed
	This is used to verify that the user cannot create a moon where it's name is too long and that the user receives an appropriate error message
		Given The user is logged in
		And The user sets the dropdown to display the moon creation menu
		When The user enters "<Thirty one char long name>"
		When The user enters "<Planet foreign key>" as the Orbited Planet ID
		When The user uploads "<Celestial body file name>" as Image
		When The user clicks on the Submit Moon button
		Then The user should be informed that the moon addition failed

	Examples: 
		| Thirty one char long name       | Planet foreign key | Celestial body file name |
		| ThisCelestialBodyNameIsTooLong! | 1                  | moon-1.jpg               |

	@PTA-TC-73 @JREQ-PTA-36
	Scenario Outline: As a user, I want to receive an error message when attempting to create a moon with a picture that is too large to persist within our database so that I know the moon addition failed
	This is to verify that a user cannot add an image that is too large to our database, and that the user receives an appropriate error message as a result
		Given The user is logged in
		And The user sets the dropdown to display the moon creation menu
		When The user enters "<New moon name>" as the Moon Name
		When The user enters "<Planet foreign key>" as the Orbited Planet ID
		When The user uploads "<Celestial body too large file>" as Image
		When The user clicks on the Submit Moon button
		Then The user should be informed that the moon addition failed

	Examples: 
		| New moon name                  | Planet foreign key | Celestial body too large file |
		| ThisIsTheSupererestMoonName!!! | 1                  | Large-picture.jpg             |
		| A                              | 2                  | Large-picture.jpg             |