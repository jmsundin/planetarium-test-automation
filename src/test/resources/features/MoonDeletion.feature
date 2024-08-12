@MoonDeletion
Feature: MoonDeletion

	@PTA-TC-50 @JREQ-PTA-58
	Scenario Outline: As a user, I want to be able to delete moons from the planetarium using their names so that the correct moon will be removed from the planetarium
	System should only remove the moon with the associated name.
		Given The user is logged in
		When User enters "<moon>" as the moon name
		When User clicks on the delete moon button
		Then The moon "<moon>" should be deleted from the Planetarium

	Examples: 
		| moon |
		| Luna |

	@PTA-TC-52 @JREQ-PTA-59
	Scenario Outline: As a user, I want to be able to delete moons from the planetarium using their ids so that the correct moon will be removed from the planetarium
	System should only remove the moon with the associated id.
		Given The user is logged in
		When User enters "<moon>" as the moon name
		When User clicks on the delete moon button
		Then The moon "<moon>" should be deleted from the Planetarium

	Examples: 
		| moon |
		| 1    |

	@PTA-TC-57 @JREQ-PTA-56
	Scenario Outline: As a user, I want to receive an error message if I attempt to remove a moon using an invalid name so I am aware that moon deletion failed
	System should not delete any planets if the input name is not valid, and should display an error message letting the user know that the deletion failed.
		Given The user is logged in
		When User enters "<moon>" as the moon name
		When User clicks on the delete moon button
		Then An error message should be displayed and the moon "<moon>" should not be deleted

	Examples: 
		| moon      |
		| fake moon |

	@PTA-TC-59 @JREQ-PTA-57
	Scenario Outline: As a user, I want to receive an error message if I attempt to remove a moon using an invalid id so I am aware that moon deletion failed
	System should not delete any moons if the id input is not valid, and should display an error message letting the user know that the deletion failed.
		Given The user is logged in
		When User enters "<moon>" as the moon name
		When User clicks on the delete moon button
		Then An error message should be displayed and the moon "<moon>" should not be deleted

	Examples: 
		| moon |
		| 200  |

	@PTA-TC-60 @JREQ-PTA-63
	Scenario Outline: As a user, I want to receive an error message if I attempt to remove a planet using the moon deletion feature so I am aware that deletion failed
	System should not delete planets while moon deletion is selected, and should display an error message letting the user know that the deletion failed.
		Given The user is logged in
		When User enters "<moon>" as the moon name
		When User clicks on the delete moon button
		Then An error message should be displayed and the moon "<moon>" should not be deleted

	Examples: 
		| moon  |
		| Earth |

	@PTA-TC-75 @JREQ-PTA-78
	Scenario Outline: As a user, I want to be able to delete moons from the planetarium with special characters as their names so that the correct moon will be removed from the planetarium
	System should only remove the moon with the associated name.
		Given The user is logged in
		When User enters "<moon>" as the moon name
		When User clicks on the delete moon button
		Then The moon "<moon>" should be deleted from the Planetarium

	Examples: 
		| moon |
		| @#%$ |

	@PTA-TC-76 @JREQ-PTA-79
	Scenario Outline: As a user, I want to be able to delete moons from the planetarium with numbers as their names so that the correct moon will be removed from the planetarium
	System should only remove the moon with the associated name.
		Given The user is logged in
		When User enters "<moon>" as the moon name
		When User clicks on the delete moon button
		Then The moon "<moon>" should be deleted from the Planetarium

	Examples: 
		| moon |
		| 1472 |

	@PTA-TC-77 @JREQ-PTA-80
	Scenario Outline: As a user, I want to receive an error message if I attempt to remove a moon while leaving the input field blank so I am aware that moon deletion failed
	System should not delete any planets if the input field is blank, and should display an error message letting the user know that the deletion failed.
		Given The user is logged in
		When User clicks on the delete moon button
		Then An error message should be displayed

	Examples: 
		| moon |