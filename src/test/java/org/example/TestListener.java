package org.example;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.testng.ScreenShooter;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends BaseTest implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult){
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog (String message){
        return message;
    }

    @Attachment
    public byte[] takeScreenshot(){
        return Selenide.screenshot(OutputType.BYTES);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        takeScreenshot();
    }



    public void onTestStart(ITestResult iTestResult) {
        System.out.println((String.format("======================================== STARTING TEST %s ========================================", iTestResult.getName())));
    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println(String.format("======================================== FINISHED TEST %s ========================================", iTestResult.getName()));
    }

}