package com.revature.planetarium.planet;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.planetarium.repository.planet.PlanetDao;

import com.revature.planetarium.repository.planet.PlanetDaoImp;
import com.revature.planetarium.service.planet.PlanetService;
import com.revature.planetarium.service.planet.PlanetServiceImp;
import org.junit.*;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class PlanetServiceTest {
    /*
    * CreatePlanet
    *   Positive
    *       ~Name length of one
    *       ~Name length of thirty
    *   ~Negative name of length zero
    *   ~Negative name length greater than 30
    *   ~Negative not unique planet names used
    *
    * SelectPlanet
    *   ~Positive
    *   ~Negative String Name doesn't exist
    *   ~Negative ID does not exist
    *   ~Negative non float/ string passed in
    *
    * selectAllPlanets
    *   ~Positive test
    *
    * selectByOwner
    *   ~Positive test
    *
    * updatePlanet(?)
    *   ~Positive test
    *       ~Name length of one
    *       ~Name length of thirty
    *   ~Negative ID does not exist
    *   ~Negative name of length zero
    *   ~Negative name length greater than 30
    *   ~Negative empty optional sent
    *   ~Negative not unique planet names used
    *
    * deletePlanet
    *   ~Positive
    *   ~Negative String Name doesn't exist
    *   ~Negative ID does not exist
    *   ~Negative non float/ string passed in
    *
    * */

    public static PlanetDao mockDao;
    public static PlanetService realService;

    public static String empty_string = "";
    public static String one_length_string = "P";
    public static String thirty_length_string = "000111222333444555666777888999";
    public static String longer_than_thirty_length_string = "This one is WAAAAYYYY TOO LONG!";
    public static String existing_planet_name = "Earth";

    public static Planet planet_to_return = new Planet();

    @BeforeClass
    public static void setup(){
        mockDao = mock(PlanetDaoImp.class);
        realService = new PlanetServiceImp(mockDao);

        // planet does not have a constructor, put it together ourselves
        planet_to_return.setPlanetId(1);
        planet_to_return.setPlanetName(existing_planet_name);
        planet_to_return.setOwnerId(1);
    }


    // Create planet
    @Test
    public void createPlanetOneLengthNamePositive(){
        // Set up out mock methods
        when(mockDao.createPlanet((Planet) notNull())).thenReturn(Optional.ofNullable(planet_to_return));
        // Create the moon to pass into the service
        Planet newPlanet = new Planet();
        newPlanet.setPlanetName(one_length_string);
        // Call the create planet method
        realService.createPlanet(newPlanet);
        verify(mockDao).createPlanet(newPlanet);
    }

    @Test
    public void createPlanetThirtyLengthNamePositive(){
        // Set up out mock methods
        when(mockDao.createPlanet((Planet) notNull())).thenReturn(Optional.ofNullable(planet_to_return));
        // Create the moon to pass into the service
        Planet newPlanet = new Planet();
        newPlanet.setPlanetName(thirty_length_string);
        // Call the create planet method
        realService.createPlanet(newPlanet);
        verify(mockDao).createPlanet(newPlanet);
    }

    @Test
    public void createPlanetZeroNameNegative(){
        // Set up out mock methods
        when(mockDao.createPlanet((Planet) notNull())).thenReturn(Optional.ofNullable(planet_to_return));
        // Create the moon to pass into the service
        Planet noNamePlanet = new Planet();
        noNamePlanet.setPlanetName(empty_string);
        // Call the create planet method
        Exception e = Assert.assertThrows(PlanetFail.class, () -> realService.createPlanet(noNamePlanet));
        Assert.assertEquals("Planet name must be between 1 and 30 characters", e.getMessage());
    }

    @Test
    public void createPlanetLongNameNegative(){
        // Set up out mock methods
        when(mockDao.createPlanet((Planet) notNull())).thenReturn(Optional.ofNullable(planet_to_return));
        // Create the moon to pass into the service
        Planet longNamePlanet = new Planet();
        longNamePlanet.setPlanetName(longer_than_thirty_length_string);
        // Call the create planet method
        Exception e = Assert.assertThrows(PlanetFail.class, () -> realService.createPlanet(longNamePlanet));
        Assert.assertEquals("Planet name must be between 1 and 30 characters", e.getMessage());

    }

    @Test
    public void createPlanetNonUniqueNameNegative(){
        // Set up out mock methods
        when(mockDao.createPlanet((Planet) notNull())).thenReturn(Optional.ofNullable(planet_to_return));
        when(mockDao.readPlanet(anyString())).thenReturn(Optional.ofNullable(planet_to_return));

        // Create the moon to pass into the service
        Planet earthPlanet = new Planet();
        earthPlanet.setPlanetName(existing_planet_name);
        // Call the create planet method
        Exception e = Assert.assertThrows(PlanetFail.class, () -> realService.createPlanet(earthPlanet));
        Assert.assertEquals("Planet name must be unique", e.getMessage());

    }

    // Select planet
    @Test
    public void selectPlanetPositive(){
        Assert.fail("Not implemented");

    }

    @Test
    public void selectPlanetNonExistentStringNameNegative(){
        Assert.fail("Not implemented");

    }

    @Test
    public void selectPlanetNonExistentIDNegative(){
        Assert.fail("Not implemented");

    }

    @Test
    public void selectPlanetNeitherStringNorIntNegative(){
        Assert.fail("Not implemented");

    }

    // Select all planets
    @Test
    public void selectAllPlanetsPositive(){
        Assert.fail("Not implemented");

    }

    // selectByOwner
    @Test
    public void selectByOwnerPositive(){
        Assert.fail("Not implemented");

    }

    // updatePlanet
    @Test
    public void updatePlanetOneLengthNamePositive(){
        Assert.fail("Not implemented");

    }

    @Test
    public void updatePlanetThirtyLengthNamePositive(){
        Assert.fail("Not implemented");

    }

    @Test
    public void updatePlanetNonExistentID(){
        Assert.fail("Not implemented");

    }

    @Test
    public void updatePlanetZeroNameNegative(){
        Assert.fail("Not implemented");

    }

    @Test
    public void updatePlanetLongNameNegative(){
        Assert.fail("Not implemented");

    }

    @Test
    public void updatePlanetEmptyOptionalNegative(){
        Assert.fail("Not implemented");

    }

    @Test
    public void updatePlanetNonUniqueNameNegative(){
        Assert.fail("Not implemented");

    }

    // deletePlanet
    @Test
    public void deletePlanetPositive(){
        Assert.fail("Not implemented");

    }

    @Test
    public void deletePlanetNonExistentStringNameNegative(){
        Assert.fail("Not implemented");

    }

    @Test
    public void deletePlanetNonExistentIDNegative(){
        Assert.fail("Not implemented");

    }

    @Test
    public void deletePlanetNeitherStringNorIntNegative(){
        Assert.fail("Not implemented");

    }
}
