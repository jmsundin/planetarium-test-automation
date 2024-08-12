package com.revature.step;

import com.revature.Setup;

import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.revature.TestRunner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.File;

public class MoonAdditionSteps {

    @Given("The user sets the dropdown to display the moon creation menu")
    public void the_user_sets_the_dropdown_to_display_the_moon_creation_menu() {
        //TestRunner.planetarium.selectOptionFromLocationSelect("Planet");
        TestRunner.planetarium.selectOptionFromLocationSelect("Moon");

    }

    @When("The user enters {string} as the Moon Name")
    public void the_user_enters_as_the_Moon_Name(String string) {
        try {
            TestRunner.planetarium.sendMoonNameToMoonNameInput(string);
        } catch(NoSuchElementException e){
            Assert.fail(e.getMessage());
        }
    }

    @When("The user enters {string} as the Orbited Planet ID")
    public void the_user_enters_as_the_Orbited_Planet_ID(String string) {
        try {
            TestRunner.planetarium.sendIdToOrbitedPlanetInput(string);
        } catch(NoSuchElementException e){
            Assert.fail(e.getMessage());
        }
    }

    @When("The user uploads {string} as Image")
    public void the_user_uploads_as_Image(String string) {
        try {
            TestRunner.planetarium.moonImageUpload(string);
        } catch(NoSuchElementException e){
            Assert.fail(e.getMessage());
        }
    }

    @When("The user clicks on the Submit Moon button")
    public void the_user_clicks_on_the_Submit_Moon_button() {
        try{
            TestRunner.planetarium.clickSubmitButton();
        } catch(NoSuchElementException e){
            Assert.fail(e.getMessage());
        }
    }

    @Then("The moon {string} should be added to the planetarium")
    public void the_moon_should_be_added_to_the_planetarium(String string) {
        String xpath = "//tr[td[1][text()='moon'] and td[3][text()='" + string + "']]";

        try {
            try {
                TestRunner.driver.switchTo().alert().accept();
            } catch (NoAlertPresentException e){

            }

            WebElement moon = TestRunner.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            Assert.assertTrue(moon.isDisplayed());
        } catch (TimeoutException e){
            Assert.fail(e.getMessage());
        }
    }

    @Then("The user should be informed that the moon addition failed")
    public void the_user_should_be_informed_that_the_moon_addition_failed() {
        try {
            TestRunner.wait.until(ExpectedConditions.alertIsPresent());
            String alertText = TestRunner.driver.switchTo().alert().getText();
            TestRunner.driver.switchTo().alert().accept();
            Assert.assertEquals("Something went wrong, please try again", alertText);
        } catch (TimeoutException e){
            Assert.fail(e.getMessage());
        }
    }
}
