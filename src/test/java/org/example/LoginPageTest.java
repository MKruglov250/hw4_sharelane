package org.example;

import lombok.extern.log4j.Log4j2;
import org.example.model.UserModelBuilder;
import org.example.utilities.LoginUtils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class LoginPageTest extends BaseTest {

    LoginPage loginPage = new LoginPage();

    @BeforeClass(alwaysRun = true)
    public void logBeforeClass(){
        log.info("Started LoginPage test suite");
    }

    @BeforeMethod(alwaysRun = true, description = "Open main page")
    public void openMainPage(){
        open("cgi-bin/main.py");
    }

    @Test(groups = "Smoke", description = "Login with admin credentials")
    public void checkAdminDataLogin() throws IOException, ParseException {
        Assert.assertTrue(loginPage.checkLoginToSite(UserModelBuilder.getAdminUser()));
        log.info("Login with Admin credentials from JSON storage checked");
    }
    @Test(groups = "Smoke",priority = 1, description = "Login with simple user credentials")
    public void checkLoginWithValidCredentialsSucceeds() throws IOException, ParseException {
        Assert.assertTrue(loginPage.checkLoginOrRegisterAndLogin());
        log.info("Login with Valid user credentials checked");
    }

    @Test(groups = "Smoke",priority = 2, description = "Check login with incorrect user")
    public void checkLoginForIncorrectUser() {
        Assert.assertFalse(loginPage.checkLoginToSite(UserModelBuilder.getIncorrectUser()));
        log.info("Login with Invalid user credentials checked");
    }

    @AfterMethod(alwaysRun = true, description = "Log out from website")
    public void logout(){
        if (loginPage.logoutLink.exists()){
            LoginUtils.logout();
        }
    }

    @AfterClass(alwaysRun = true)
    public void logAfterClass(){
        log.info("Finished LoginPage test suite");
    }

}