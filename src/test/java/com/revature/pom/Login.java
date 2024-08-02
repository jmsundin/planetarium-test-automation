package com.revature.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
    private WebDriver driver;
    private String url = "http://localhost:8080/";

    @FindBy(id = "usernameInput")
    private WebElement usernameInput;
    @FindBy(id = "passwordInput")
    private WebElement passwordInput;
    @FindBy(xpath = "//*[@id=\"loginForm\"]/input[3]")
    private WebElement loginButton;
    @FindBy(tagName = "a")
    private WebElement createAnAccountLink;

    public Login(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToLoginPage(){
        driver.get(url);
    }

    public void sendUsernameToUsernameInput(String username){
        usernameInput.sendKeys(username);
    }

    public void sendPasswordToPasswordInput(String password){
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton(){
        loginButton.click();
    }

    public void clickCreateAnAccountLink(){
        createAnAccountLink.click();
    }

}
