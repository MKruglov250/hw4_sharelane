package org.example;

import org.example.model.UserModelBuilder;
import org.example.utilities.LoginUtils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
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

    @Test
    public void checkAdminDataLogin() throws IOException, ParseException {
        Assert.assertTrue(loginPage.checkLoginToSite(UserModelBuilder.getAdminUser()));
        if (loginPage.logoutLink.exists()){
            LoginUtils.logout();
        }
    }
    @Test
    public void checkLoginWithValidCredentialsSucceeds() throws IOException, ParseException {
        Assert.assertTrue(loginPage.checkLoginOrRegisterAndLogin());
        if (loginPage.logoutLink.exists()){
            LoginUtils.logout();
        }
    }

    @Test
    public void checkLoginForIncorrectUser() {
        Assert.assertFalse(loginPage.checkLoginToSite(UserModelBuilder.getIncorrectUser()));
    }

}