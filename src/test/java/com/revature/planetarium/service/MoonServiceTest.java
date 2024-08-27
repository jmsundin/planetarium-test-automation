package com.revature.planetarium.service;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.revature.Setup;
import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.planetarium.repository.moon.MoonDao;
import com.revature.planetarium.repository.moon.MoonDaoImp;
import com.revature.planetarium.service.moon.MoonService;
import com.revature.planetarium.service.moon.MoonServiceImp;
import org.junit.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class MoonServiceTest {

    private MoonDao moonDao;
    private MoonService moonService;

    public static String moonNameTooShort = "";
    public static String moonNameOneCharacter = "a";
    public static String moonName30Characters = "123456789012345678901234567890";
    public static String moonNameTooLong = "1234567890123456789012345678901";
    public static String moonNameNotUnique = "Luna";
    public static int nonUniqueMoonId = 1;
    public static int uniqueMoonId = 20;
    public static int validPlanetId = 1;
    public static int invalidPlanetId = 100;
    public static Moon moonToReturn = new Moon(1, "a", 1);

    @BeforeClass
    public static void setup(){
        // TODO
    }

    @Before
    public void beforeEach(){
        Setup.resetTestDatabase();
        this.moonDao = createMock();
        this.moonService = new MoonServiceImp(moonDao);
    }

    public MoonDao createMock(){
        MoonDao mockDao = mock(MoonDaoImp.class);
        when(mockDao.createMoon((Moon)notNull())).thenReturn(Optional.ofNullable(moonToReturn));

        when(mockDao.readMoon(anyInt())).thenReturn(Optional.empty());
        when(mockDao.readMoon("a")).thenReturn(Optional.empty());
        when(mockDao.readMoon(1)).thenReturn(Optional.ofNullable(moonToReturn));
        when(mockDao.readMoon("b")).thenReturn(Optional.ofNullable(moonToReturn));

        when(mockDao.readAllMoons()).thenReturn(new ArrayList<Moon>());
        when(mockDao.readMoonsByPlanet(anyInt())).thenReturn(new ArrayList<Moon>());
        when(mockDao.updateMoon((Moon)notNull())).thenReturn(Optional.ofNullable(moonToReturn));
        doReturn(true).when(mockDao).deleteMoon(anyInt());
        doReturn(true).when(mockDao).deleteMoon(anyString());
        return mockDao;
    }
    /*
        Tests:

        createMoon(Moon moon)
            1. moon name is 0 characters
            2. moon name is 1 character
            3. moon name is 30 characters
            4. moon name is 31 characters
            5. moon name is not unique
     */

    @Test
    public void createMoon_name_too_short(){
        Moon testMoon = new Moon(uniqueMoonId, moonNameTooShort, validPlanetId);
        try{
            Moon newMoon = moonService.createMoon(testMoon);
            Assert.fail();
        }catch(MoonFail e){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void createMoon_name_one_character(){
        Moon testMoon = new Moon(uniqueMoonId, moonNameOneCharacter, validPlanetId);
        //doReturn(Optional.ofNullable(moonToReturn)).when(moonDao).readMoon("a");
        try{
            Moon newMoon = moonService.createMoon(testMoon);
            Assert.assertNotNull(newMoon.getMoonName(), moonNameOneCharacter);
        }catch(MoonFail e){
            System.out.println(e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void createMoon_name_30_characters(){
        Moon testMoon = new Moon(uniqueMoonId, moonName30Characters, validPlanetId);
        try{
            Moon newMoon = moonService.createMoon(testMoon);
            Assert.assertTrue(true);
        }catch(MoonFail e){
            Assert.fail();
        }
    }

    @Test
    public void createMoon_name_too_long(){
        Moon testMoon = new Moon(uniqueMoonId, moonNameTooLong, validPlanetId);
        try{
            Moon newMoon = moonService.createMoon(testMoon);
            Assert.fail();
        }catch(MoonFail e){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void createMoon_name_not_unique(){
        Moon testMoon = new Moon(uniqueMoonId, moonNameOneCharacter, validPlanetId);
        try{
            Moon newMoon = moonService.createMoon(testMoon);
            Assert.assertEquals(newMoon.getMoonName(), moonNameOneCharacter);
        }catch(MoonFail e){
            System.out.println(e.getMessage());
            Assert.fail();
        }
    }

    /*
        selectMoon(T idOrName)
            1. moon name is string
            2. moon name is int
            3. moon name is double
    */
    @Test
    public void selectMoon_moon_name_is_string(){
        try{
            Moon moon = moonService.selectMoon("b");
            Assert.assertTrue(true);
        }catch(MoonFail e){
            System.out.println(e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void selectMoon_moon_name_is_int(){
        try{
            Moon moon = moonService.selectMoon(1);
            Assert.assertTrue(true);
        }catch(MoonFail e){
            System.out.println(e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void selectMoon_moon_name_is_double(){
        try{
            Moon moon = moonService.selectMoon(2.6);
            Assert.fail();
        }catch(MoonFail e){
            System.out.println(e.getMessage());
            Assert.assertTrue(true);
        }
    }

    /*
        selectAllMoons()
            1. check if a list of moons was returned
    */

    @Test
    public void selectAllMoons_test(){
        try{
            List<Moon> moons = moonService.selectAllMoons();
            Assert.assertNotNull(moons);
        }catch(MoonFail e){
            Assert.fail();
        }

    }


    /*
        selectByPlanet(int planetId)
            1. should return a list
    */

    @Test
    public void selectByPlanet_id_exists(){
        try{
            List<Moon> moons = moonService.selectByPlanet(validPlanetId);
            Assert.assertNotNull(moons);
        }catch(MoonFail e){
            Assert.fail();
        }

    }

    /*
        updateMoon(Moon moon)
            1. id is not valid
            2. id is valid
            3. moon name is 0 characters
            4. moon name is 1 character
            5. moon name is 30 characters
            6. moon name is 31 characters
            7. moon name is not unique
    */

    @Test
    public void updateMoon_id_not_valid(){
        Moon testMoon = new Moon(uniqueMoonId, moonName30Characters, validPlanetId);
        try{
            Moon newMoon = moonService.updateMoon(testMoon);
            Assert.fail();
        }catch(MoonFail e){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void updateMoon_id_valid(){
        Moon testMoon = new Moon(nonUniqueMoonId, moonName30Characters, validPlanetId);
        try{
            Moon newMoon = moonService.updateMoon(testMoon);
            Assert.assertTrue(true);
        }catch(MoonFail e){
            Assert.fail();
        }
    }

    @Test
    public void updateMoon_name_too_short(){
        Moon testMoon = new Moon(nonUniqueMoonId, moonNameTooShort, validPlanetId);
        try{
            Moon newMoon = moonService.updateMoon(testMoon);
            Assert.fail();
        }catch(MoonFail e){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void updateMoon_name_one_character(){
        Moon testMoon = new Moon(nonUniqueMoonId, moonNameOneCharacter, validPlanetId);
        try{
            Moon newMoon = moonService.updateMoon(testMoon);
            Assert.assertTrue(true);
        }catch(MoonFail e){
            Assert.fail();
        }
    }

    @Test
    public void updateMoon_name_30_characters(){
        Moon testMoon = new Moon(nonUniqueMoonId, moonName30Characters, validPlanetId);
        try{
            Moon newMoon = moonService.updateMoon(testMoon);
            Assert.assertTrue(true);
        }catch(MoonFail e){
            Assert.fail();
        }
    }

    @Test
    public void updateMoon_name_too_long(){
        Moon testMoon = new Moon(nonUniqueMoonId, moonNameTooLong, validPlanetId);
        try{
            Moon newMoon = moonService.updateMoon(testMoon);
            Assert.fail();
        }catch(MoonFail e){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void updateMoon_name_not_unique(){
        Moon testMoon = new Moon(nonUniqueMoonId, moonNameNotUnique, validPlanetId);
        try{
            Moon newMoon = moonService.updateMoon(testMoon);
            Assert.assertTrue(true);
        }catch(MoonFail e){
            System.out.println(e.getMessage());
            Assert.fail();
        }
    }

    /*
        deleteMoon(T idOrName)
            1. moon name is string
            2. moon name is int
            3. moon name is double
    */
    @Test
    public void deleteMoon_name_is_string(){
        try{
            String returned = moonService.deleteMoon("this is a string");
            Assert.assertEquals(returned, "Moon deleted successfully");
        }catch(MoonFail e){
            Assert.fail();
        }
    }

    @Test
    public void deleteMoon_name_is_int(){
        try{
            String returned = moonService.deleteMoon(1);
            Assert.assertEquals(returned, "Moon deleted successfully");
        }catch(MoonFail e){
            Assert.fail();
        }
    }

    @Test
    public void deleteMoon_name_is_double(){
        try{
            String returned = moonService.deleteMoon(2.6);
            Assert.fail();
        }catch(MoonFail e){
            Assert.assertTrue(true);
        }
    }
}
