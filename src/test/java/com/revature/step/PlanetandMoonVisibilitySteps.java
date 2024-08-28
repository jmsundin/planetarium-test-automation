package com.revature.step;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.revature.TestRunner;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class PlanetandMoonVisibilitySteps {
   
    @When("The user directly goes to the Home page of Planetarium {string}")
    public void the_user_directly_goes_to_the_home_page_of_planetarium(String url){
        TestRunner.setup();
        TestRunner.driver.get(url);
    }

    @Then("The user should be informed they need to login first")
    public void the_user_should_be_informed_they_need_to_login_first(){
        String prompt = TestRunner.driver.findElement(By.tagName("pre")).getText();
        Assert.assertTrue(prompt.contains("Please log in first"));
    }

    @Then("The user should see the moon called {string} and owner ID {string}")
    public void the_user_should_see_the_moon_called_Pre_existing_moon_moon_ID_Moon_ID_and_owner_ID_Planet_Id(String moonName, String planetID){
        String xpath = "//tr[td[1][text()='moon'] and td[3][text()='%s'] and td[4][text()='%s']]".formatted(moonName, planetID);
        WebElement moon = null;
        try {
            moon = TestRunner.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        if (moon == null) {
            Assert.fail("Moon not found");
        }
        Assert.assertTrue(moon.isDisplayed());
    }

    @Then("The logged in user should see the planet {string} and owner ID {string}")
    public void the_logged_in_user_should_see_the_planet_from_other_user(String planetName, String ownerID){
        String xpath = "//tr[td[1][text()='planet'] and td[3][text()='%s'] and td[4][text()='%s']]".formatted(planetName, ownerID);
        String planet = "";
        try {
            planet = TestRunner.wait.until(d -> d.findElement(By.xpath(xpath)).getText());
        } catch (Exception e) {
            Assert.fail("Planet not found");
        }
        if (planet.equals("")) {
            Assert.fail("Planet not found");
        }
        Assert.assertTrue(planet.contains(planetName));
        Assert.assertTrue(planet.contains(ownerID));
    }
}
