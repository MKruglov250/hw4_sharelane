package org.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.example.utilities.PropertyReader;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;


import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class BaseTest {

    @BeforeSuite
    public void setSystemProperties(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        System.setProperty("currenttime", dateFormat.format(new Date()));
    }

    @Attachment("Info File")
    @Step
    public static byte[] attachInfoFile () throws IOException{
        log.info("Attaching Info log to Allure");
        return Files.readAllBytes(Paths.get("src/logs", "sharelane-info.log"));
    }

    @Attachment("Debug File")
    @Step
    public static byte[] attachDebugFile () throws IOException{
        log.info("Attaching Info log to Allure");
        return Files.readAllBytes(Paths.get("src/logs", "sharelane-debug.log"));
    }

    @Attachment
    @Step("Get provided Resource file and attach it to report")
    public static byte[] getFileBytes(String resourceName) throws IOException{
        log.info("Attaching Properties file");
        return Files.readAllBytes(Paths.get("src/resources", resourceName));
    }


    @BeforeClass(alwaysRun = true, description = "Initializing Herokuapp site and setting up Browser" +
            "and WebDriver settings before suite start")
    public void before() throws IOException {
        log.info("Starting configuring web driver");
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
        log.info("Web driver configuration complete");
    }

    @AfterSuite
    public void attachSharelaneLogFiles() throws IOException {
        attachDebugFile();
        attachInfoFile();
    }

}
