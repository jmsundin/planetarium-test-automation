package com.revature.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Register {
    private WebDriver driver;
    private String url = "http://localhost:8080/register";

    @FindBy(id = "usernameInput")
    private WebElement usernameInput;
    @FindBy(id = "passwordInput")
    private WebElement passwordInput;
    @FindBy(xpath = "//*[@id=\"loginForm\"]/input[3]")
    private WebElement createButton;

    public Register(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToRegistrationPage(){
        driver.get(url);
    }

    public void sendUsernameToUsernameInput(String username){
        usernameInput.sendKeys(username);
    }

    public void sendPasswordToPasswordInput(String password){
        passwordInput.sendKeys(password);
    }

    public void clickCreateButton(){
        createButton.click();
    }

}

