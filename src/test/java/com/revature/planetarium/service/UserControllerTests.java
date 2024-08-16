package com.revature.planetarium.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.Optional;

import com.revature.planetarium.entities.User;
import com.revature.planetarium.exceptions.UserFail;
import com.revature.planetarium.repository.user.UserDao;
import com.revature.planetarium.service.user.UserService;
import com.revature.planetarium.service.user.UserServiceImp;

public class UserControllerTests {

    private UserDao userDao;
    private UserService userService;

    private int id1 = 1;
    private String validUsername1 = "Planets and Moons are awesomee";
    private String validPassword1 = "Planets and Moons are awesomee";

    @BeforeClass
    public static void setup(){
        // TODO
    }

    @Before
    public void beforeEach(){
        userDao = mock(UserDao.class);
        userService = new UserServiceImp(userDao);
    }

    /*
        NOTE: a test is considered to pass if no exception is thrown by the test method
    */
    @Test
    public void createUserSuccessTest(){
        User newUser = new User(id1, validUsername1, validPassword1);

        when(userDao.findUserByUsername(validUsername1)).thenReturn(Optional.empty());
        when(userDao.createUser(newUser)).thenReturn(Optional.of(newUser));

        String result = userService.createUser(newUser);
        assertEquals("Created user with username " + validUsername1 + " and password " + validPassword1, result);
    }

    @Test
    public void createUserFailTest(){
        User newUser = new User(id1, validUsername1, validPassword1);
        when(userDao.findUserByUsername(validUsername1)).thenReturn(Optional.empty());
        when(userDao.createUser(newUser)).thenReturn(Optional.empty());

        Exception exception = assertThrows(UserFail.class, () -> userService.createUser(newUser));
        assertEquals("Failed to create user, please try again", exception.getMessage());
    }

    @Test
    public void usernameTooShortTest(){
        int id = 2;
        User newUser = new User(id, "", validPassword1);
        Exception exception = assertThrows(UserFail.class, () -> userService.createUser(newUser));
        assertEquals("Username must be between 1 and 30 characters", exception.getMessage());
    }

    @Test
    public void usernameTooLongTest(){
        // TODO
    }

    @Test
    public void passwordTooShortTest(){
        // TODO
    }

    @Test
    public void passwordTooLongTest(){
        // TODO
    }

    @Test
    public void usernameAlreadyExistsTest(){
        // TODO
    }

    @Test
    public void authenticateSuccessTest(){
        // TODO
    }

    @After
    public void afterEach(){
        // TODO
    }

    @AfterClass
    public static void afterAll(){
        // TODO
    }
}
