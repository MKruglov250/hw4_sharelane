package org.example;

import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class RegistrationTest extends BaseTest {

    Registration registration = new Registration();

    @BeforeMethod(alwaysRun = true, description = "Open registration page")
    public void openRegistrationPage(){
        open("cgi-bin/register.py");
    }

    @Test(groups = "Smoke", description = "Check entering correct zip-code")
    public void checkCorrectZipCodeEntry(){
        Assert.assertTrue(registration.submitValidZipCode());
    }

    // Fails ATM as Password field is in fact not hidden - bug
    @Test(groups = "Regression", description = "Check Password field is masked")
    public void checkPasswordFieldHidden(){
        registration.submitValidZipCode();
        Assert.assertTrue(registration.isInputFieldHidden("password1"));
    }

    @Test(groups = "Regression", description = "Check Confirm Password field is hidden")
    public void checkConfirmPasswordFieldHidden(){
        registration.submitValidZipCode();
        Assert.assertTrue(registration.isInputFieldHidden("password2"));
    }

    @Test(groups = "Regression",
            description = "Check registering with valid credentials succeeds")
    public void checkRegisterWithValidCredentials(){
        registration.submitValidZipCode();
        registration.enterValidCredentials();
        Assert.assertTrue(registration.submitCredentials());
    }

}