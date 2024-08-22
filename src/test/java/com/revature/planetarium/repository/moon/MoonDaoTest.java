package com.revature.planetarium.repository.moon;

import java.io.IOException;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.Setup;
import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.MoonFail;


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

    @Test
    public void testCreateMoonWithImagePositive(){
        validMoon.setImageData(moonImage);
        Optional<Moon> newMoon = moonDao.createMoon(validMoon);
        Assert.assertNotNull(newMoon);
        Assert.assertEquals("Triton", newMoon.get().getMoonName());
    }

    @Test
    public void testCreateMoonNoImagePositive(){
        Optional<Moon> newMoon = moonDao.createMoon(validMoon);
        Assert.assertNotNull(newMoon);
        Assert.assertEquals("Triton", newMoon.get().getMoonName());
    }

    @Test
    public void testCreateMoonNegative(){
        Moon moon = new Moon();
        Assert.assertThrows(MoonFail.class, ()->{
            moonDao.createMoon(moon);
        });
    }

    @Test
    public void testReadMoonIDPositive(){
        Optional<Moon> checkMoon = moonDao.readMoon(1);
        Assert.assertNotNull(checkMoon);
        Assert.assertEquals(existingMoon, checkMoon.get());
    }

    @Test
    public void testReadMoonIDNegative(){
        Optional<Moon> notExistingMoon = moonDao.readMoon(100);
        Assert.assertFalse(notExistingMoon.isPresent());
    }


}
