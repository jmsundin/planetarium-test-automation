package com.revature.planetarium.repository.moon;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;

import com.revature.Setup;
import com.revature.planetarium.entities.Moon;


public class MoonDaoTest {

    private MoonDao moonDao;
    static String moonImage;
    private Moon validMoon;
    private Moon existingMoon;

    @BeforeClass
    public static void imageSetup(){
        try{
            moonImage = Setup.convertToBase64("src/test/resources/Celestial-Images/moon-1.jpg");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Before
    public void setup(){
        Setup.resetTestDatabase();
        moonDao = new MoonDaoImp();
        createValidMoon();
        createExistingMoon();
    }

    public void createValidMoon(){
        validMoon = new Moon();
        validMoon.setMoonName("Triton");
        validMoon.setOwnerId(1);
    }

    public void createExistingMoon(){
        existingMoon = new Moon(1,"Luna",1);
        existingMoon.setImageData(moonImage);
    }
}
