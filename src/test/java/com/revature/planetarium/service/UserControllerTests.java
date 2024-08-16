package com.revature.planetarium.service;

import org.junit.*;

public class UserControllerTests {
    
    @BeforeClass
    public static void setup(){
        System.out.println("This runs before all tests");
    }

    @Before
    public void beforeEach(){
        System.out.println("This runs before each test");
    }

    /*
        NOTE: a test is considered to pass if no exception is thrown by the test method
    */
    @Test
    public void createUserSuccessTest(){
        // TODO
    }

    @Test
    public void createUserFailTest(){
        // TODO
    }

    @Test
    public void usernameTooShortTest(){
        // TODO
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
        System.out.println("This runs after each test");
    }

    @AfterClass
    public static void afterAll(){
        System.out.println("This runs after all tests");
    }
}
