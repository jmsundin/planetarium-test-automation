package com.revature.step;

import com.revature.Setup;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.revature.TestRunner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
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

    @When("The user uploads {string} as the image")
    public void the_user_uploads_as_the_image(String planetImage) {
        TestRunner.planetarium.planetImageUpload(planetImage);
    }

    @When("The user clicks on the Submit Planet button")
    public void the_user_clicks_on_the_Submit_Planet_button() {
        TestRunner.planetarium.clickSubmitButton();
    }

    @Then("The user should be informed that the planet addition failed")
    public void the_user_should_be_informed_that_the_planet_addition_failed() {
        TestRunner.wait.until(ExpectedConditions.alertIsPresent());
        String alert = TestRunner.driver.switchTo().alert().getText();
        TestRunner.driver.switchTo().alert().accept();
        Assert.assertTrue(alert.contains("please try again"));
    }
}
