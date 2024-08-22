package com.revature.step;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.revature.TestRunner;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MoonDeletionSteps {


    @When("user clicks on the delete text entry field")
    public void user_clicks_on_the_delete_text_entry_field(){
        TestRunner.planetarium.clickDeleteInput();
    }

    @When("User enters {string} as the moon name")
    public void user_enters_string_as_the_moon_name(String moonName){
        TestRunner.planetarium.sendCelestialNameToDeleteInput(moonName);
    }

    @When("User clicks on the delete moon button")
    public void user_clicks_on_the_delete_moon_button(){
        TestRunner.planetarium.clickDeleteButton();
    }

    @Then("The moon {string} should be deleted from the Planetarium")
    public void that_moon_should_be_deleted_from_the_Planetarium(String moonName){
        try{
            TestRunner.wait.until(ExpectedConditions.alertIsPresent());
            String alert = TestRunner.driver.switchTo().alert().getText();
            TestRunner.driver.switchTo().alert().accept();
        }catch (TimeoutException e){
        }

        try{
            String xpath = "//tr[td[1][text()='moon'] and td[3][text()='%s']]".formatted(moonName);
            WebElement moon = TestRunner.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            Assert.assertFalse(moon.isDisplayed());
        }catch (TimeoutException e){
            Assert.assertFalse(false);
        }

    }

    @Then("An error message should be displayed and the moon {string} should not be deleted")
    public void an_error_message_should_be_displayed_and_no_moon_should_be_deleted(String moonName){
        try{
            TestRunner.wait.until(ExpectedConditions.alertIsPresent());
        }catch (TimeoutException e){
            Assert.fail(e.getMessage());
        }
        String alert = TestRunner.driver.switchTo().alert().getText();
        TestRunner.driver.switchTo().alert().accept();
        try{
            String xpath = "//tr[td[3][text()='%s']]".formatted(moonName);
            WebElement moon = TestRunner.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            Assert.assertTrue(alert.contains("Failed to delete") && moon.isDisplayed());
        }catch(TimeoutException e){
            Assert.fail(e.getMessage());
        }

    }

    @Then("The user should be informed that moon deletion failed")
    public void the_user_should_be_informed_that_moon_deletion_failed(){
        try{
            TestRunner.wait.until(ExpectedConditions.alertIsPresent());
        }catch (TimeoutException e){
            Assert.fail(e.getMessage());
        }
        String alert = TestRunner.driver.switchTo().alert().getText();
        TestRunner.driver.switchTo().alert().accept();
        Assert.assertTrue(alert.contains("Failed to delete"));
    }
}
