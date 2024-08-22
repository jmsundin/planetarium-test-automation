package com.revature.planetarium.planet;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.planetarium.repository.planet.PlanetDao;

import com.revature.planetarium.repository.planet.PlanetDaoImp;
import com.revature.planetarium.service.planet.PlanetService;
import com.revature.planetarium.service.planet.PlanetServiceImp;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*
 * createPlanet
 *   Positive
 *       ~Name length of one
 *       ~Name length of thirty
 *   ~Negative name of length zero
 *   ~Negative name length greater than 30
 *   ~Negative not unique planet names used
 *
 * selectPlanet
 *   ~Positive
 *       By string
 *       By int
 *   ~Negative String Name doesn't exist
 *   ~Negative ID does not exist
 *   ~Negative non int/ string passed in
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
 *   ~Negative not unique planet names used
 *
 * deletePlanet
 *   ~Positive
 *   ~Negative String Name doesn't exist
 *   ~Negative ID does not exist
 *   ~Negative non float/ string passed in
 *
 * */


public class PlanetServiceTest {

    public static PlanetDao mockDao;
    public static PlanetService realService;

    public static String empty_string = "";
    public static String one_length_string = "P";
    public static String thirty_length_string = "000111222333444555666777888999";
    public static String longer_than_thirty_length_string = "This one is WAAAAYYYY TOO LONG!";
    public static String existing_planet_name = "Earth";
    public static String not_real_planet_name = "I am not real";

    public static int existing_id = 1;
    public static int not_real_id = 0;

    public static Planet planet_to_return = new Planet();

    public static List<Planet> planetList = new ArrayList<>(Arrays.asList(new Planet(), new Planet(), new Planet()));

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
    public void selectPlanetByStringPositive(){
        when(mockDao.readPlanet(anyString())).thenReturn(Optional.ofNullable(planet_to_return));

        Planet returnedPlanet = realService.selectPlanet(existing_planet_name);

        Assert.assertNotNull(returnedPlanet);
    }

    @Test
    public void selectPlanetByIntPositive(){
        when(mockDao.readPlanet(anyInt())).thenReturn(Optional.ofNullable(planet_to_return));

        Planet returnedPlanet = realService.selectPlanet(existing_id);

        Assert.assertNotNull(returnedPlanet);
    }

    @Test
    public void selectPlanetNonExistentStringNameNegative(){
        when(mockDao.readPlanet(anyString())).thenReturn(Optional.empty());

        Exception e = Assert.assertThrows(PlanetFail.class, () -> realService.selectPlanet(not_real_planet_name));
        Assert.assertEquals("Planet not found", e.getMessage());
    }

    @Test
    public void selectPlanetNonExistentIDNegative(){
        when(mockDao.readPlanet(anyInt())).thenReturn(Optional.empty());

        Exception e = Assert.assertThrows(PlanetFail.class, () -> realService.selectPlanet(not_real_id));
        Assert.assertEquals("Planet not found", e.getMessage());
    }

    @Test
    public void selectPlanetNeitherStringNorIntNegative(){
        // No need to mock any methods

        Exception e = Assert.assertThrows(PlanetFail.class, () -> realService.selectPlanet(Math.PI));
        Assert.assertEquals("identifier must be an Integer or String", e.getMessage());
    }

    // Select all planets
    @Test
    public void selectAllPlanetsPositive(){
        when(mockDao.readAllPlanets()).thenReturn(planetList);

        List<Planet> returnedList = realService.selectAllPlanets();
        Assert.assertEquals(planetList, returnedList);
    }

    // selectByOwner
    @Test
    public void selectByOwnerPositive(){
        when(mockDao.readPlanetsByOwner(anyInt())).thenReturn(planetList);

        List<Planet> returnedList = realService.selectByOwner(existing_id);
        Assert.assertEquals(planetList, returnedList);
    }

    // updatePlanet
    @Test
    public void updatePlanetOneLengthNamePositive(){
        // Mock methods
        when(mockDao.readPlanet(anyInt())).thenReturn(Optional.of(planet_to_return));
        when(mockDao.readPlanet(anyString())).thenReturn(Optional.empty()); // Empty since we aren't testing matching names
        when(mockDao.updatePlanet((Planet) notNull())).thenReturn(Optional.of(planet_to_return));

        // Define data entree parameters
        Planet updatingPlanet = new Planet();
        updatingPlanet.setPlanetName(one_length_string);
        updatingPlanet.setPlanetId(1);
        updatingPlanet.setOwnerId(1);

        // Call methods
        Planet returnedPlanet = realService.updatePlanet(updatingPlanet);

        // Check output
        Assert.assertNotNull(returnedPlanet);
    }

    @Test
    public void updatePlanetThirtyLengthNamePositive(){
        // Mock methods
        when(mockDao.readPlanet(anyInt())).thenReturn(Optional.of(planet_to_return));
        when(mockDao.readPlanet(anyString())).thenReturn(Optional.empty()); // Empty since we aren't testing matching names
        when(mockDao.updatePlanet((Planet) notNull())).thenReturn(Optional.of(planet_to_return));

        // Define data entree parameters
        Planet updatingPlanet = new Planet();
        updatingPlanet.setPlanetName(thirty_length_string);
        updatingPlanet.setPlanetId(1);
        updatingPlanet.setOwnerId(1);

        // Call methods
        Planet returnedPlanet = realService.updatePlanet(updatingPlanet);

        // Check output
        Assert.assertNotNull(returnedPlanet);

    }

    @Test
    public void updatePlanetZeroNameNegative(){
        when(mockDao.readPlanet(anyInt())).thenReturn(Optional.of(planet_to_return));

        // Define data entree parameters
        Planet updatingPlanet = new Planet();
        updatingPlanet.setPlanetName(empty_string);
        updatingPlanet.setPlanetId(1);
        updatingPlanet.setOwnerId(1);

        // Call methods
        Exception e = Assert.assertThrows(PlanetFail.class, () -> realService.updatePlanet(updatingPlanet));
        Assert.assertEquals("Planet name must be between 1 and 30 characters, could not update", e.getMessage());

    }

    @Test
    public void updatePlanetLongNameNegative(){
        when(mockDao.readPlanet(anyInt())).thenReturn(Optional.of(planet_to_return));

        // Define data entree parameters
        Planet updatingPlanet = new Planet();
        updatingPlanet.setPlanetName(longer_than_thirty_length_string);
        updatingPlanet.setPlanetId(1);
        updatingPlanet.setOwnerId(1);

        // Call methods
        Exception e = Assert.assertThrows(PlanetFail.class, () -> realService.updatePlanet(updatingPlanet));
        Assert.assertEquals("Planet name must be between 1 and 30 characters, could not update", e.getMessage());

    }

    @Test
    public void updatePlanetNonExistentIDNegative(){
        when(mockDao.readPlanet(anyInt())).thenReturn(Optional.empty());

        // Define data entree parameters
        Planet updatingPlanet = new Planet();
        updatingPlanet.setPlanetName(thirty_length_string);
        updatingPlanet.setPlanetId(1);
        updatingPlanet.setOwnerId(1);

        // Call methods
        Exception e = Assert.assertThrows(PlanetFail.class, () -> realService.updatePlanet(updatingPlanet));
        Assert.assertEquals("Planet not found, could not update", e.getMessage());
    }

    @Test
    public void updatePlanetNonUniqueNameNegative(){
        // Set the "Old planet" to a name that is different from what we are comparing to
        Planet old_planet = new Planet();
        old_planet.setPlanetId(1);
        old_planet.setPlanetName("Crab");
        when(mockDao.readPlanet(anyInt())).thenReturn(Optional.of(old_planet));

        // Set up a planet with a different ID than old planet with the name we are trying to update the planet too
        Planet diff_ID_matching_name_planet = new Planet();
        diff_ID_matching_name_planet.setPlanetId(2);
        diff_ID_matching_name_planet.setPlanetName(existing_planet_name);
        when(mockDao.readPlanet(anyString())).thenReturn(Optional.of(diff_ID_matching_name_planet));

        Exception e = Assert.assertThrows(PlanetFail.class, () -> realService.updatePlanet(planet_to_return));
        Assert.assertEquals("Planet name must be unique, could not update", e.getMessage());
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
