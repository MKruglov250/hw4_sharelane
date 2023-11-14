package org.example;

import org.example.model.UserModelBuilder;
import org.example.utilities.LoginUtils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

public class LoginPageTest extends BaseTest {

    LoginPage loginPage = new LoginPage();
    @BeforeMethod
    public void openMainPage(){
        open("cgi-bin/main.py");
    }

    @Test(priority = 0)
    public void checkAdminDataLogin() throws IOException, ParseException {
        Assert.assertTrue(loginPage.checkLoginToSite(UserModelBuilder.getAdminUser()));
    }
    @Test(priority = 1)
    public void checkLoginWithValidCredentialsSucceeds() throws IOException, ParseException {
        Assert.assertTrue(loginPage.checkLoginOrRegisterAndLogin());
    }

    @Test(priority = 2)
    public void checkLoginForIncorrectUser() {
        Assert.assertFalse(loginPage.checkLoginToSite(UserModelBuilder.getIncorrectUser()));
    }

    @AfterMethod
    public void logout(){
        if (loginPage.logoutLink.exists()){
            LoginUtils.logout();
        }
    }

}