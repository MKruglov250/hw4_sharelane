package org.example;

import org.example.Utilities.LoginUtils;
import org.example.Utilities.RegistrationUtils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

public class LoginPageTest extends BaseTest {

    LoginPage loginPage = new LoginPage();

    @BeforeClass
    public void openMainPage(){
        open("cgi-bin/main.py");
    }

    @Test
    public void checkLoginWithValidCredentialsSucceeds() throws IOException, ParseException {
        Assert.assertTrue(loginPage.checkLoginOrRegisterAndLogin());
    }

    @AfterClass
    public void doLogout(){
        LoginUtils.logout();
    }


}