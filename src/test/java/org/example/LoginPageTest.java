package org.example;

import org.example.model.UserModelBuilder;
import org.example.utilities.LoginUtils;
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

    @Test
    public void checkLoginForAdminAccount() throws IOException, ParseException {
        Assert.assertTrue(loginPage.checkLoginToSite(UserModelBuilder.getAdminUser()));
    }

    @Test
    public void checkLoginForIncorrectUser() throws IOException, ParseException {
        Assert.assertTrue(loginPage.checkLoginToSite(UserModelBuilder.getIncorrectUser()));
    }

    @AfterClass
    public void doLogout(){
        LoginUtils.logout();
    }


}