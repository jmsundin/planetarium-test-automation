package com.revature.planetarium.repository.planet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.Setup;
import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.planetarium.repository.planet.PlanetDao;
import com.revature.planetarium.utility.DatabaseConnector;

public class PlanetDaoTest {

    private PlanetDao planetDao;
    static String planetImage;
    private Planet validPlanet;
    private Planet existingPlanet;

    @BeforeClass
    public static void imageSetup(){
        try{
            planetImage = Setup.convertToBase64("src/test/resources/Celestial-Images/planet-1.jpg");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Before
    public void setup(){
        Setup.resetTestDatabase();
        planetDao = new PlanetDaoImp();
        createValidPlanet();
        createExistingPlanet();
    }

    public void createValidPlanet(){
        validPlanet = new Planet();
        validPlanet.setPlanetName("Earth");
        validPlanet.setOwnerId(1);
    }

    public void createExistingPlanet(){
        existingPlanet = new Planet();
        existingPlanet.setPlanetName("Earth");
        existingPlanet.setOwnerId(1);
    }

    @Test
    public void testCreatePlanetWithImagePositive(){
        validPlanet.setImageData(planetImage);
        Optional<Planet> newPlanet = planetDao.createPlanet(validPlanet);
        Assert.assertNotNull(newPlanet);
        Assert.assertEquals("Triton", newPlanet.get().getPlanetName());
    }

    @Test
    public void testCreatePlanetNoImagePositive(){
        Optional<Planet> newPlanet = planetDao.createPlanet(validPlanet);
        Assert.assertNotNull(newPlanet);
        Assert.assertEquals("Triton", newPlanet.get().getPlanetName());
    }

    @Test
    public void testCreatePlanetNegative(){
        Planet planet = new Planet();
        Assert.assertThrows(PlanetFail.class, ()->{
            planetDao.createPlanet(planet);
        });
    }

    @Test
    public void testReadPlanetIDPositive(){
        Optional<Planet> checkPlanet = planetDao.readPlanet(1);
        Assert.assertNotNull(checkPlanet);
        Assert.assertEquals(existingPlanet, checkPlanet.get());
    }

    @Test
    public void testReadPlanetIDNegative(){
        Optional<Planet> notExistingPlanet = planetDao.readPlanet(100);
        Assert.assertFalse(notExistingPlanet.isPresent());
    }

    @Test
    public void testReadPlanetNamePositive(){
        Optional<Planet> checkPlanet = planetDao.readPlanet("Earth");
        Assert.assertNotNull(checkPlanet);
        Assert.assertEquals(existingPlanet, checkPlanet.get());

    }

    @Test
    public void testReadPlanetNameNegative(){
        Optional<Planet> notExistingPlanet = planetDao.readPlanet("Not a Planet");
        Assert.assertFalse(notExistingPlanet.isPresent());
    }

     @Test
    public void testGetAllPlanetsPositive(){
        Assert.assertTrue(planetDao.readAllPlanets().size() >= 4);
    }

    @Test
    public void testGetAllPlanetsNegative(){
        deletePlanetsForNegativeGetAllPlanetsTest();
        Assert.assertEquals(0, planetDao.readAllPlanets().size());
    }

    // helper method for negative getAllPlanets test
    public void deletePlanetsForNegativeGetAllPlanetsTest() {
        try(Connection connection = DatabaseConnector.getConnection()){
            String sql = "delete from planets";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e){
            throw new AssertionError("Could not delete planets");
        }
    }

    @Test
    public void testUpdatePlanetNamePositive(){
        existingPlanet.setPlanetName("Mars");
        Assert.assertSame(existingPlanet, planetDao.updatePlanet(existingPlanet).get());
    }

    @Test
    public void testUpdatePlanetPlanetPositive(){
        existingPlanet.setOwnerId(2);
        Assert.assertSame(existingPlanet, planetDao.updatePlanet(existingPlanet).get());
    }

    @Test
    public void testUpdatePlanetNegative(){
        Planet planet = new Planet();
        Assert.assertSame(Optional.empty(),planetDao.updatePlanet(planet));
    }

    @Test
    public void testDeletePlanetByIDPositive(){
        Assert.assertTrue(planetDao.deletePlanet(1));
        Assert.assertEquals(3, planetDao.readAllPlanets().size());
        Assert.assertSame(Optional.empty(),planetDao.readPlanet(1));
    }

    @Test
    public void testDeletePlanetByIDNegative(){
        Assert.assertFalse(planetDao.deletePlanet(100));
        Assert.assertEquals(4, planetDao.readAllPlanets().size());
    }

    @Test
    public void testDeletePlanetByNamePositive(){
        Assert.assertTrue(planetDao.deletePlanet("Earth"));
        Assert.assertEquals(3, planetDao.readAllPlanets().size());
        Assert.assertSame(Optional.empty(),planetDao.readPlanet(1));
    }

    @Test
    public void testDeletePlanetByNameNegative(){
        Assert.assertFalse(planetDao.deletePlanet("Not a Planet"));
        Assert.assertEquals(4, planetDao.readAllPlanets().size());
    }
}
