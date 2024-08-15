package com.revature;

import java.time.Duration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.revature.pom.Login;
import com.revature.pom.Planetarium;
import com.revature.pom.Register;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    //features = "src/test/resources/features/MoonAddition.feature",
    //features = "src/test/resources/features/PlanetDeletion.feature",
    // features = "src/test/resources/features/PlanetandMoonVisibility.feature",
    features = "classpath:features",
    glue = "com.revature.step",
    plugin = {
        "pretty",
        "html:src/test/resources/reports/html-report.html",
        "json:src/test/resources/reports/json-report.json"
    }
)
public class TestRunner {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Login login;
    public static Register register;
    public static Planetarium planetarium;

    @BeforeClass
    public static void setup(){
        //Setup.resetTestDatabase();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        login = new Login(driver);
        register = new Register(driver);
        planetarium = new Planetarium(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterClass
    public static void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }

}
