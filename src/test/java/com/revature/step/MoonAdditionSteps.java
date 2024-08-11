package com.revature.step;

import com.revature.Setup;

import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.revature.TestRunner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.File;

public class MoonAdditionSteps {

    @Given("The user sets the dropdown to display the moon creation menu")
    public void the_user_sets_the_dropdown_to_display_the_moon_creation_menu() {
        TestRunner.planetarium.selectOptionFromLocationSelect("Planet");
        TestRunner.planetarium.selectOptionFromLocationSelect("Moon");

        /*try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
    }

    @When("The user enters {string} as the Moon Name")
    public void the_user_enters_as_the_Moon_Name(String string) {
        TestRunner.planetarium.sendMoonNameToMoonNameInput(string);
    }

    @When("The user enters {string} as the Orbited Planet ID")
    public void the_user_enters_as_the_Orbited_Planet_ID(String string) {
        TestRunner.planetarium.sendIdToOrbitedPlanetInput(string);
    }

    @When("The user uploads {string} as Image")
    public void the_user_uploads_as_Image(String string) {
        TestRunner.planetarium.moonImageUpload(string);

    }

    @When("The user clicks on the Submit Moon button")
    public void the_user_clicks_on_the_Submit_Moon_button() {
        TestRunner.planetarium.clickSubmitButton();
    }

    @Then("The moon {string} should be added to the planetarium")
    public void the_moon_should_be_added_to_the_planetarium(String string) {
        String xpath = "//tr[td[1][text()='moon'] and td[3][text()='" + string + "']]";
        WebElement moon = TestRunner.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        Assert.assertTrue(moon.isDisplayed());
        System.out.println("test done");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("The user should be informed that the moon addition failed")
    public void the_user_should_be_informed_that_the_moon_addition_failed() {
        TestRunner.wait.until(ExpectedConditions.alertIsPresent());
        String alertText = TestRunner.driver.switchTo().alert().getText();
        TestRunner.driver.switchTo().alert().accept();
        //System.out.println(alertText);
        Assert.assertEquals("Something went wrong, please try again", alertText);
    }

}
