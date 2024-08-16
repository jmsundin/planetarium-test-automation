package com.revature.planetarium.user;

import com.revature.Setup;
import com.revature.planetarium.entities.User;
import com.revature.planetarium.exceptions.UserFail;
import com.revature.planetarium.repository.user.UserDao;
import com.revature.planetarium.repository.user.UserDaoImp;
import org.junit.*;

import java.util.Optional;

public class UserDaoTest {

    public static UserDao userDao;

    public static String username1 = "Paul";
    public static String password1 = "Password";

    public static String tooLongString = "123456789012345678901234567890!";

    public static String existingUsername = "Batman";

    public static String nonExistentUser = "This is isn't real";

    @BeforeClass
    public static void setup(){
        // Generate in static to spend less time creating the same object again and again
        userDao = new UserDaoImp();
    }

    @AfterClass
    public static void tearDown(){

    }

    @Before
    public void beforeEach(){
        Setup.resetTestDatabase();
    }

    @After
    public void afterEach(){

    }

    /*
    * Methods to test
    *   public Optional<User> createUser(User newUser)
    *   public Optional<User> findUserByUsername(String username)
    *
    * Ideas for potential tests
    *   Positive createUser
    *   Negative createUser
    *       Too long username,
    *       No username,
    *       Too long password,
    *       No password
    *
    *   Positive findUserByUsername
    *   Negative findUserByUsername
    *       No username input
    *       Username does not exist within database
    * */

    @Test
    public void createUserPositive(){
        User newUser = new User();
        newUser.setUsername(username1);
        newUser.setPassword(password1);

        Optional<User> returnUser = userDao.createUser(newUser);

        Assert.assertTrue(returnUser.isPresent());
    }

    @Test
    public void createUserTooLongUsername(){
        try{
            User newUser = new User();
            newUser.setUsername(tooLongString);
            newUser.setPassword(password1);

            Optional<User> returnUser = userDao.createUser(newUser);
            if(returnUser.isPresent()){
                Assert.fail("User persisted with password longer than 30 characters");
            }
            
        } catch(UserFail ignore){ }

    }

    @Test
    public void createUserTooLongPassword(){
        try{
            User newUser = new User();
            newUser.setUsername(username1);
            newUser.setPassword(tooLongString);

            Optional<User> returnUser = userDao.createUser(newUser);
            if(returnUser.isPresent()){
                Assert.fail("User persisted with password longer than 30 characters");
            }
        } catch(UserFail ignore){ }
    }

    @Test
    public void findUserByUsernamePositive(){

        Optional<User> existingUser = userDao.findUserByUsername(existingUsername);
        Assert.assertTrue(existingUser.isPresent());
    }

    @Test
    public void findUserByUsernameNoUsername(){
        try{
            Optional<User> existingUser = userDao.findUserByUsername("");
            if(existingUser.isPresent()){
                Assert.fail();
            }
        } catch (UserFail ignore){ }
    }

    @Test
    public void findUserByUsernameNonExistent(){
        try{
            Optional<User> existingUser = userDao.findUserByUsername(nonExistentUser);
            if(existingUser.isPresent()){
                Assert.fail();
            }
        } catch (UserFail ignore){ }
    }
}

/*
    @Test
    public void testExample(){

    }
*/
