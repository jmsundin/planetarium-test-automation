package com.revature.planetarium.planet;

import com.revature.planetarium.repository.planet.PlanetDao;

import com.revature.planetarium.repository.planet.PlanetDaoImp;
import org.junit.*;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;


public class PlanetServiceTest {
    /*
    * CreatePlanet
    *   ~Positive
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

    public static PlanetDao dao;

    @BeforeClass
    public static void setup(){
        dao = mock(PlanetDaoImp.class);
    }


    // Create planet
    @Test
    public void createPlanetPositive(){

    }

    @Test
    public void createPlanetZeroNameNegative(){

    }

    @Test
    public void createPlanetLongNameNegative(){

    }

    @Test
    public void createPlanetNonUniqueNameNegative(){

    }

    // Select planet
    @Test
    public void selectPlanetPositive(){

    }

    @Test
    public void selectPlanetNonExistentStringNameNegative(){

    }

    @Test
    public void selectPlanetNonExistentIDNegative(){

    }

    @Test
    public void selectPlanetNeitherStringNorIntNegative(){

    }

    // Select all planets
    @Test
    public void selectAllPlanetsPositive(){

    }

    // selectByOwner
    @Test
    public void selectByOwnerPositive(){

    }

    // updatePlanet
    @Test
    public void updatePlanetPositive(){

    }

    @Test
    public void updatePlanetNonExistentID(){

    }

    @Test
    public void updatePlanetZeroNameNegative(){

    }

    @Test
    public void updatePlanetLongNameNegative(){

    }

    @Test
    public void updatePlanetEmptyOptionalNegative(){

    }

    @Test
    public void updatePlanetNonUniqueNameNegative(){

    }

    // deletePlanet
    @Test
    public void deletePlanetPositive(){

    }

    @Test
    public void deletePlanetNonExistentStringNameNegative(){

    }

    @Test
    public void deletePlanetNonExistentIDNegative(){

    }

    @Test
    public void deletePlanetNeitherStringNorIntNegative(){

    }
}
