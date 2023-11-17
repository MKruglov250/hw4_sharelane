package org.example.utils;

import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println(String.format("======================================== FAILED TEST %s ========================================", iTestResult.getName()));
        takeScreenshot(iTestResult);
    }

    private byte[] takeScreenshot(ITestResult iTestResult) {
        WebDriver driver = getWebDriver();
        try {
            if(driver != null) {
                return AllureUtils.takeScreenshot(driver);
            } else {
                return new byte[] {};
            }
        } catch (NoSuchSessionException | IllegalStateException ex) {
            return new byte[] {};
        }
    }

    public void onTestStart(ITestResult iTestResult) {
        System.out.println((String.format("======================================== STARTING TEST %s ========================================", iTestResult.getName())));
    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println(String.format("======================================== FINISHED TEST %s Duration: ========================================", iTestResult.getName()));
    }

}
