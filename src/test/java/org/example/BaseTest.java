package org.example;

import com.codeborne.selenide.Configuration;
import org.example.utilities.PropertyReader;
import org.testng.annotations.BeforeTest;

import java.io.FileNotFoundException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {

    @BeforeTest(alwaysRun = true, description = "Initializing Herokuapp site and setting up Browser" +
            "and WebDriver settings before suite start")
    public void before() throws FileNotFoundException {

        Configuration.baseUrl = "https://www.sharelane.com/";
        Configuration.browser = PropertyReader.getBrowserProperty();
        Configuration.headless = false;
        open(".");

        getWebDriver().manage().window().maximize();
        getWebDriver().manage().timeouts().implicitlyWait(Duration
                .ofSeconds(PropertyReader.getTimeoutProperty()));
    }
}
