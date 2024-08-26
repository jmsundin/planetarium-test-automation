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
    
}
