package org.example;

import lombok.extern.log4j.Log4j2;
import org.example.utilities.LoginUtils;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class MainPageTest extends BaseTest {

    MainPage mainPage = new MainPage();

    @BeforeClass(alwaysRun = true)
    public void logBeforeClass(){
        log.info("Started MainPage test suite");
    }
    @BeforeMethod(alwaysRun = true)
    public void openPage() {
        open("cgi-bin/main.py");
    }

    // Checking elements on Main Page
    @Test(groups = "Smoke", description = "Checking that two books appear")
    public void testMainPageBooksExist() {
        Assert.assertTrue(mainPage.getFirstBookImage().exists());
        Assert.assertTrue(mainPage.getSecondBookImage().exists());
        log.info("Checked that main page has 2 books highlighted");
    }

    @Test(groups = "Smoke", description = "Checking search button on place")
    public void testSearchButtonExists() {
        Assert.assertTrue(mainPage.getSearchInputButton().exists());
        log.info("Checked that Search button exists on Main page");
    }

    @Test(groups = "Smoke", description = "Checking search input field on place")
    public void testSearchInputFieldExists() {
        Assert.assertTrue(mainPage.getSearchInputControl().exists());
        log.info("Checked that Search input field exists on Main page");
    }

    @Test(groups = "Smoke", description = "Checking registration controls")
    public void testRegistrationControlExists() {
        Assert.assertTrue(mainPage.getRegisterLink().exists());
        log.info("Checked that Registration control exists on Main page");
    }

    @Test(groups = "Smoke", description = "Checking login/password controls")
    public void testLoginControlsExist() {
        Assert.assertTrue(mainPage.getMainPageLogin().exists());
        Assert.assertTrue(mainPage.getMainPagePassword().exists());
        Assert.assertTrue(mainPage.getLoginButton().exists());
        log.info("Checked that Login, Password fields and Login Button exist");
    }

    // Key pages navigation

    @Test(groups = "Smoke", description = "Checking shopping cart navigation")
    public void testShoppingCartNavigation() {
        mainPage.getShoppingCartLink().click();
        Assert.assertEquals("https://www.sharelane.com/cgi-bin/shopping_cart.py",
                webdriver().object().getCurrentUrl());
        log.info("Checked that Shopping Cart link exists and is correct");
    }

    @Test(groups = "Smoke", description = "Checking book page navigation")
    public void testBookPageNavigation() {
        mainPage.getFirstBookImage().click();
        Assert.assertTrue(webdriver().object().getCurrentUrl().contains("https://www.sharelane.com/cgi-bin/show_book.py"));
        log.info("Checked that Book elements has working links to book pages");
    }

    @AfterClass(alwaysRun = true, description = "Log out from website")
    public void doLogout() {
        LoginUtils.logout();
        log.info("Finished MainPage test suite");
    }

}