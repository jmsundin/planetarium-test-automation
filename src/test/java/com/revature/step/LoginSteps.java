package com.revature.step;

import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.revature.TestRunner;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
    @When("The user enters {string} as the username")
    public void the_user_enters_as_the_username(String username) {
        TestRunner.login.sendUsernameToUsernameInput(username);
    }

    @When("The user enters {string} as the password")
    public void the_user_enters_as_the_password(String password) {
        TestRunner.login.sendPasswordToPasswordInput(password);
    }

    @When("The user clicks on the Login button")
    public void the_user_clicks_on_the_Login_button() {
        TestRunner.login.clickLoginButton();
    }

    @Then("The user should be redirected to the Login page")
    public void the_user_should_be_redirected_to_the_Login_page() {
        TestRunner.wait.until(ExpectedConditions.titleIs("Home"));
        Assert.assertEquals("Home", TestRunner.driver.getTitle());
    }

    @Then("The user should be informed that the account login failed")
    public void the_user_should_be_informed_that_the_account_login_failed() {
        TestRunner.wait.until(ExpectedConditions.alertIsPresent());
        String alert = TestRunner.driver.switchTo().alert().getText();
        TestRunner.driver.switchTo().alert().accept();
        Assert.assertTrue(alert.contains("login attempt failed"));
    }
}
