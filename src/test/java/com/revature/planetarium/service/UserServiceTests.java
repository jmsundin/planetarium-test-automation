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
import java.util.Optional;

import com.revature.planetarium.entities.User;
import com.revature.planetarium.exceptions.UserFail;
import com.revature.planetarium.repository.user.UserDao;
import com.revature.planetarium.service.user.UserService;
import com.revature.planetarium.service.user.UserServiceImp;

public class UserServiceTests {

    private UserDao userDao;
    private UserService userService;

    private User validUser;
    private User existingUser;
    private User userWithNoUsername;
    private User userWithNoPassword;
    private User userWithUsernameTooLong;
    private User userWithPasswordTooLong;

    @Before
    public void setup(){
        userDao = mock(UserDao.class);
        userService = new UserServiceImp(userDao);

        validUser = new User(1, "Planets and Moons are awesomee", "Planets and Moons are awesomee");
        existingUser = new User(2, "Batman", "I am the night");
        userWithNoUsername = new User(3, "", "Planets and Moons are awesomee");
        userWithNoPassword = new User(4, "Planets and Moons are awesomee", "");
        userWithUsernameTooLong = new User(5, "Planets and Moons are awesomee and I like to write long sentences", "Planets and Moons are awesomee");
        userWithPasswordTooLong = new User(6, "Planets and Moons are awesomee", "Planets and Moons are awesomee and I like to write long sentences");
    }

    /*
        NOTE: a test is considered to pass if no exception is thrown by the test method
    */
    @Test
    public void createUserSuccessTest(){
        when(userDao.createUser(validUser)).thenReturn(Optional.of(validUser));

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
        int id = 3;
        User newUser = new User(id, "a".repeat(31), validPassword1);
        Exception exception = assertThrows(UserFail.class, () -> userService.createUser(newUser));
        assertEquals("Username must be between 1 and 30 characters", exception.getMessage());
    }

    @Test
    public void passwordTooShortTest(){
        int id = 4;
        User newUser = new User(id, validUsername1, "");
        Exception exception = assertThrows(UserFail.class, () -> userService.createUser(newUser));
        assertEquals("Password must be between 1 and 30 characters", exception.getMessage());
    }

    @Test
    public void passwordTooLongTest(){
        int id = 5;
        User newUser = new User(id, validUsername1, "a".repeat(31));
        Exception exception = assertThrows(UserFail.class, () -> userService.createUser(newUser));
        assertEquals("Password must be between 1 and 30 characters", exception.getMessage());
    }

    @Test
    public void usernameAlreadyExistsTest(){
        int id = 6;
        User newUser = new User(id, existingUsername, validPassword1);
        when(userDao.findUserByUsername(existingUsername)).thenReturn(Optional.of(newUser));

        Exception exception = assertThrows(UserFail.class, () -> userService.createUser(newUser));
        assertEquals("Username is already in use", exception.getMessage());
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
