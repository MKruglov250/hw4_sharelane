package org.example;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {

    @BeforeSuite(description = "Initializing Herokuapp site and setting up Browser" +
            "and WebDriver settings before suite start")
    public void before() {

        Configuration.baseUrl = "https://www.sharelane.com/";
        Configuration.browser = "Chrome";
        Configuration.headless = false;
        open(".");
        getWebDriver().manage().window().maximize();
        getWebDriver().manage().timeouts().implicitlyWait(Duration
                .ofSeconds(3));
    }
}
