package org.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.example.utilities.PropertyReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;


import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class BaseTest {

    @Attachment
    @Step("Get provided Resource file and attach it to report")
    public static byte[] getFileBytes(String resourceName) throws IOException{
        return Files.readAllBytes(Paths.get("src/resources", resourceName));
    }

    @BeforeClass(alwaysRun = true, description = "Initializing Herokuapp site and setting up Browser" +
            "and WebDriver settings before suite start")
    public void before() throws IOException {
        Configuration.screenshots = true;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
                .includeSelenideSteps(true));


        getFileBytes("config.properties");

        Configuration.baseUrl = "https://www.sharelane.com/";
        Configuration.browser = PropertyReader.getBrowserProperty();
        Configuration.headless = false;
        open(".");



        getWebDriver().manage().window().maximize();
        getWebDriver().manage().timeouts().implicitlyWait(Duration
                .ofSeconds(PropertyReader.getTimeoutProperty()));
    }
}
