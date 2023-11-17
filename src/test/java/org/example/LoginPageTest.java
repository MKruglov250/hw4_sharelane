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
    @BeforeMethod(alwaysRun = true, description = "Open main page")
    public void openMainPage(){
        open("cgi-bin/main.py");
    }

    @Test(groups = "Smoke", description = "Login with admin credentials")

    public void checkAdminDataLogin() throws IOException, ParseException {
        Assert.assertTrue(loginPage.checkLoginToSite(UserModelBuilder.getAdminUser()));
    }
    @Test(groups = "Smoke",priority = 1, description = "Login with simple user credentials")
    public void checkLoginWithValidCredentialsSucceeds() throws IOException, ParseException {
        Assert.assertTrue(loginPage.checkLoginOrRegisterAndLogin());
    }

    @Test(groups = "Smoke",priority = 2, description = "Check login with incorrect user")
    public void checkLoginForIncorrectUser() {
        Assert.assertFalse(loginPage.checkLoginToSite(UserModelBuilder.getIncorrectUser()));
    }

    @AfterMethod(alwaysRun = true, description = "Log out from website")
    public void logout(){
        if (loginPage.logoutLink.exists()){
            LoginUtils.logout();
        }
    }

}