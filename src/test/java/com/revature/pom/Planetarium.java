package com.revature.pom;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Planetarium {
    private WebDriver driver;
    private final String URL = "http://localhost:8080/planetarium";

    @FindBy(id = "locationSelect")
    private WebElement locationSelect;
    @FindBy(id = "deleteInput")
    private WebElement deleteInput;
    @FindBy(id="deleteButton")
    private WebElement deleteButton;

    @FindBy(id = "planetNameInput")
    private WebElement planetNameInput;
    @FindBy(id = "planetImageInput")
    private WebElement planetImageInput;

    @FindBy(id = "moonNameInput")
    private WebElement moonNameInput;
    @FindBy(id = "orbitedPlanetInput")
    private WebElement orbitedPlanetInput;
    @FindBy(id = "moonImageInput")
    private WebElement moonImageInput;
    @FindBy(id = "celestialTable")
    private WebElement celestialTable;

    @FindBy(xpath = "//*[@id=\"inputContainer\"]/button")
    private WebElement submitButton;

    public Planetarium(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToPlanetariumPage(){
        driver.get(URL);
    }

    public void selectOptionFromLocationSelect(String celestialBody){
        Select optionSelect = new Select(locationSelect);
        optionSelect.selectByVisibleText(celestialBody);
    }

    public void sendCelestialNameToDeleteInput(String username){
        deleteInput.sendKeys(username);
    }

    public void clickDeleteButton(){
        deleteButton.click();
    }

    public void sendMoonNameToMoonNameInput(String moonName){
        moonNameInput.sendKeys(moonName);
    }

    public void sendIdToOrbitedPlanetInput(String id){
        orbitedPlanetInput.sendKeys(id);
    }

    public void moonImageUpload(String imageName){
        File uploadFile = new File("src/test/resources/Celestial-Images/" + imageName);
        moonImageInput.sendKeys(uploadFile.getAbsolutePath());
    }

    public void sendPlanetNameToPlanetNameInput(String planetName){
        planetNameInput.sendKeys(planetName);
    }

    public void planetImageUpload(String imageName){
        File uploadFile = new File("src/test/resources/Celestial-Images/" + imageName);
        planetImageInput.sendKeys(uploadFile.getAbsolutePath());
    }

    public void clickSubmitButton(){
        submitButton.click();
    }

    public void clickDeleteInput(){ deleteInput.click();}

}
