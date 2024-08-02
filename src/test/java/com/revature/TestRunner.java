package com.revature;

import java.time.Duration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.revature.pom.Login;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:features",
    glue = "com.revature.step",
    plugin = {
        "pretty",
        "html:src/test/resources/html-report.html",
        "json:src/test/resources/json-report.json"
    }
)
class TestRunner {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Login login;

    @BeforeClass
    public static void setup(){
        Setup.resetTestDatabase();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        login = new Login(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterClass
    public static void tearDown(){
        if(driver != null){
            driver.quit();
        }
    };
}
