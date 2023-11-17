package org.example;

import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class RegistrationTest extends BaseTest {

    Registration registration = new Registration();

    @BeforeClass(alwaysRun = true)
    public void logBeforeClass(){
        log.info("Started Registration test suite");
    }

    @BeforeMethod(alwaysRun = true, description = "Open registration page")
    public void openRegistrationPage(){
        open("cgi-bin/register.py");
    }

    @Test(groups = "Smoke", description = "Check entering correct zip-code")
    public void checkCorrectZipCodeEntry(){
        Assert.assertTrue(registration.submitValidZipCode());
        log.info("Checked that 5-digit zipcode is accepted by registration form");
    }

    // Fails ATM as Password field is in fact not hidden - bug
    @Test(groups = "Regression", description = "Check Password field is masked")
    public void checkPasswordFieldHidden(){
        registration.submitValidZipCode();
        Assert.assertTrue(registration.isInputFieldHidden("password1"));
        log.info("Checked that Password field on registration field is hidden");
    }

    @Test(groups = "Regression", description = "Check Confirm Password field is hidden")
    public void checkConfirmPasswordFieldHidden(){
        registration.submitValidZipCode();
        Assert.assertTrue(registration.isInputFieldHidden("password2"));
        log.info("Checked that Confirm Password field on registration field is hidden");
    }

    @Test(groups = "Regression",
            description = "Check registering with valid credentials succeeds")
    public void checkRegisterWithValidCredentials(){
        registration.submitValidZipCode();
        registration.enterValidCredentials();
        Assert.assertTrue(registration.submitCredentials());
        log.info("Checked registration with Valid credentials for all registration fields");
    }

    @AfterClass(alwaysRun = true)
    public void logAfterClass(){
        log.info("Finished Registration test suite");
    }

}