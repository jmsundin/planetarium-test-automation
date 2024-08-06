package com.revature.step;

import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.revature.TestRunner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class PlanetAdditionSteps {
    @Given("The user is logged in")
    public void the_user_is_logged_in() {
        TestRunner.login.goToLoginPage();
        TestRunner.login.sendUsernameToUsernameInput("Batman");
        TestRunner.login.sendPasswordToPasswordInput("I am the night");
        TestRunner.login.clickLoginButton();
        TestRunner.wait.until(ExpectedConditions.titleIs("Home"));
        Assert.assertEquals("Home", TestRunner.driver.getTitle());
    }

    @When("The user selects the planet option from the dropdown")
    public void the_user_selects_the_planet_option_from_the_dropdown() {
       TestRunner.planetarium.selectOptionFromLocationSelect("Planet");
    }

    @When("The user enters {string} as the planet name")
    public void the_user_enters_as_the_planet_name(String planetName) {
        TestRunner.planetarium.sendPlanetNameToPlanetNameInput(planetName);
    }
}
