package com.revature.step;

import org.junit.Assert;
import org.openqa.selenium.By;

import com.revature.TestRunner;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class PlanetAndMoonVisibilitySteps {
    @When("The user directly goes to the Home page of Planetarium {string}")
    public void the_user_is_not_logged_in_and_goes_to_the_home_page_of_planetarium() {
        String currentURL = TestRunner.driver.getCurrentUrl();
        Assert.assertEquals("http://localhost:8080/planetarium", currentURL);
    }

    @Then("The user should be informed they need to login first")
    public void the_user_should_be_informed_they_need_to_login_first() {
        String prompt = TestRunner.driver.findElement(By.tagName("pre")).getText();
        Assert.assertTrue(prompt.contains("Please log in first"));
    }
}
