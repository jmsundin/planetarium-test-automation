package com.revature.step;

import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.revature.TestRunner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountCreationSteps {
    @Given("The user is on the Planetarium login page")
    public void the_user_is_on_the_Planetarium_login_page() {
        TestRunner.login.goToLoginPage();
    }

    @When("The user clicks on the create an account button")
    public void the_user_clicks_on_the_create_an_account_button() {
        TestRunner.login.clickCreateAnAccountLink();
    }

    @When("The user enters {string} as a new username")
    public void the_user_enters_as_a_new_username(String username) {
        TestRunner.register.sendUsernameToUsernameInput(username);
    }

    @When("The user enters {string} as a new password")
    public void the_user_enters_as_a_new_password(String password) {
        TestRunner.register.sendPasswordToPasswordInput(password);
    }

    @When("The user clicks the Create button")
    public void the_user_clicks_the_Create_button() {
        TestRunner.register.clickCreateButton();
    }

    @Then("The user should be informed that the account creation was successful")
    public void the_user_should_be_informed_that_the_account_creation_was_successful() {
        TestRunner.wait.until(ExpectedConditions.alertIsPresent());
        String alert = TestRunner.driver.switchTo().alert().getText();
        TestRunner.driver.switchTo().alert().accept();
        Assert.assertTrue(alert.contains("Account created successfully"));
    }

    @Then("The user should be informed that the account creation failed")
    public void the_user_should_be_informed_that_the_account_creation_failed() {
        TestRunner.wait.until(ExpectedConditions.alertIsPresent());
        String alert = TestRunner.driver.switchTo().alert().getText();
        TestRunner.driver.switchTo().alert().accept();
        Assert.assertTrue(alert.contains("Account creation failed"));
    }
}
