package com.revature.planetarium.service;

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
 *   Positive
 *      By integer
 *      By string
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

    // Rest of the code remains unchanged
    // ...
}