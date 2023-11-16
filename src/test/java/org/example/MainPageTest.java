package org.example;

import org.example.utilities.LoginUtils;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

@Listeners({TestListener.class})
public class MainPageTest extends BaseTest {

    MainPage mainPage = new MainPage();

    @BeforeMethod(alwaysRun = true)
    public void openPage(){
        open("cgi-bin/main.py");
    }

    // Checking elements on Main Page
    @Test(groups = "Smoke",description = "Checking that two books appear")
    public void testMainPageBooksExist(){
        Assert.assertTrue(mainPage.getFirstBookImage().exists());
        Assert.assertTrue(mainPage.getSecondBookImage().exists());
    }

    @Test(groups = "Smoke", description = "Checking search button on place")
    public void testSearchButtonExists(){
        Assert.assertTrue(mainPage.getSearchInputButton().exists());
    }

    @Test(groups = "Smoke", description = "Checking search input field on place")
    public void testSearchInputFieldExists(){
        Assert.assertTrue(mainPage.getSearchInputControl().exists());
    }

    @Test(groups = "Smoke", description = "Checking registration controls")
    public void testRegistrationControlExists(){
        Assert.assertTrue(mainPage.getRegisterLink().exists());
    }


    @Test(groups = "Smoke", description = "Checking login/password controls")
    public void testLoginControlsExist(){
        Assert.assertTrue(mainPage.getMainPageLogin().exists());
        Assert.assertTrue(mainPage.getMainPagePassword().exists());
        Assert.assertTrue(mainPage.getLoginButton().exists());
    }

    // Key pages navigation

    @Test(groups = "Smoke", description = "Checking shopping cart navigation")
    public void testShoppingCartNavigation(){
        mainPage.getShoppingCartLink().click();
        Assert.assertEquals(webdriver().object().getCurrentUrl(), "https://www.sharelane.com/cgi-bin/shopping_cart.py");
    }

    @Test(groups = "Smoke", description = "Checking book page navigation")
    public void testBookPageNavigation(){
        mainPage.getFirstBookImage().click();
        Assert.assertTrue(webdriver().object().getCurrentUrl().contains("https://www.sharelane.com/cgi-bin/show_book.py"));
    }

    @AfterClass(alwaysRun = true, description = "Log out from website")
    public void doLogout(){
        LoginUtils.logout();
    }

}