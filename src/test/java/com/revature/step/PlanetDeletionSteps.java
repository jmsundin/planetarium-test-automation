package com.revature.step;

import com.revature.TestRunner;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PlanetDeletionSteps {

    @When("The user sets the dropdown to display the planet creation menu")
    public void the_user_sets_the_dropdown_to_display_the_planet_creation_menu() {
        TestRunner.planetarium.selectOptionFromLocationSelect("Planet");
    }

    @When("The user enters {string} as the celestial body to be deleted")
    public void the_user_enters_as_the_celestial_body_to_be_deleted(String string) {
        TestRunner.planetarium.sendCelestialNameToDeleteInput(string);
    }

    @When("The user presses the delete button")
    public void the_user_presses_the_delete_button() {
        TestRunner.planetarium.clickDeleteButton();

    }

    @When("The user enters nothing as the celestial body to be deleted")
    public void the_user_enters_nothing_as_the_celestial_body_to_be_deleted() {
        // Do nothing :-)
    }

    @Then("The planet with the id {string} should be deleted from the Planetarium")
    public void the_planet_with_the_id_should_be_deleted_from_the_Planetarium(String string) {
        String xpath = "//tr[td[1][text()='planet'] and td[2][text()='" + string + "']]";
        WebElement element = TestRunner.driver.findElement(By.xpath(xpath));

        try{
            TestRunner.wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (TimeoutException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("The user should no longer see the planet {string} within the planetarium")
    public void the_user_should_no_longer_see_the_planet_within_the_planetarium(String string) {
        try {
            TestRunner.wait.until(ExpectedConditions.alertIsPresent());
            TestRunner.driver.switchTo().alert().accept();
            Assert.fail();
        } catch (TimeoutException ignored){}

        try{
            String xpath = "//tr[td[1][text()='planet'] and td[3][text()='" + string + "']]";
            //WebElement element = TestRunner.driver.findElement(By.xpath(xpath));
            TestRunner.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));

        } catch (TimeoutException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("The user should be informed that the planet deletion failed")
    public void the_user_should_be_informed_that_the_planet_deletion_failed() {


        try {
            TestRunner.wait.until(ExpectedConditions.alertIsPresent());
            String alertText = TestRunner.driver.switchTo().alert().getText();
            TestRunner.driver.switchTo().alert().accept();
            Assert.assertEquals("Failed to delete planet with name ", alertText);
        } catch (TimeoutException e){
            Assert.fail(e.getMessage());
        }
    }

    @Then("The user should no longer see {string} and {string} within the planetarium")
    public void the_user_should_no_longer_see_and_within_the_planetarium(String string, String string2) {
        try {
            TestRunner.driver.switchTo().alert().accept();
            Assert.fail();
        } catch (NoAlertPresentException ignored){

        }

        String planetXpath = "//tr[td[1][text()='planet'] and td[3][text()='" + string + "']]";
        String moonXpath = "//tr[td[1][text()='moon'] and td[3][text()='" + string2 + "']]";

        try{
            WebElement planetElement = TestRunner.driver.findElement(By.xpath(planetXpath));
            WebElement moonElement = TestRunner.driver.findElement(By.xpath(moonXpath));

            TestRunner.wait.until(ExpectedConditions.invisibilityOf(planetElement));
            TestRunner.wait.until(ExpectedConditions.invisibilityOf(moonElement));


        } catch (TimeoutException e){
            Assert.fail(e.getMessage());
        }
    }
}
