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

import com.revature.Setup;
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

        String result = userService.createUser(validUser);
        assertEquals("Created user with username " + validUser.getUsername() + " and password " + validUser.getPassword(), result);
    }

    @Test
    public void usernameAlreadyExistsTest(){
        when(userDao.findUserByUsername(existingUser.getUsername())).thenReturn(Optional.of(existingUser));

        Exception exception = assertThrows(UserFail.class, () -> userService.createUser(existingUser));
        assertEquals("Username is already in use", exception.getMessage());
    }

    @Test
    public void usernameTooShortTest(){
        Exception exception = assertThrows(UserFail.class, () -> userService.createUser(userWithNoUsername));
        assertEquals("Username must be between 1 and 30 characters", exception.getMessage());
    }

    @Test
    public void usernameTooLongTest(){
        Exception exception = assertThrows(UserFail.class, () -> userService.createUser(userWithUsernameTooLong));
        assertEquals("Username must be between 1 and 30 characters", exception.getMessage());
    }

    @Test
    public void passwordTooShortTest(){
        Exception exception = assertThrows(UserFail.class, () -> userService.createUser(userWithNoPassword));
        assertEquals("Password must be between 1 and 30 characters", exception.getMessage());
    }

    @Test
    public void passwordTooLongTest(){
        Exception exception = assertThrows(UserFail.class, () -> userService.createUser(userWithPasswordTooLong));
        assertEquals("Password must be between 1 and 30 characters", exception.getMessage());
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
