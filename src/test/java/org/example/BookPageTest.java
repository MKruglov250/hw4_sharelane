package org.example;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;

public class BookPageTest extends BaseTest {

    BookPage bookPage = new BookPage();
    MainPage mainPage = new MainPage();

    static int bookId;

    @BeforeClass
    public void doLogin() throws IOException, ParseException {
        open("cgi-bin/main.py");
        UtilitiesSharelane.loginOverrideExpiration();
    }

    @BeforeMethod
    public void openBookPage() {
        open("cgi-bin/main.py");
        mainPage.getFirstBookImage().click();
        bookId = Integer.parseInt(webdriver().object().getCurrentUrl().
                replaceAll("[\\D]", ""));
    }

    @Test(groups = "Smoke", description = "Test that book has correct name")
    public void checkBookName() {
        Assert.assertEquals(bookPage.getBookName(),bookPage.compareBookName(bookId));
    }

    @Test(groups = "Smoke", description = "Test that book author is correct")
    public void checkAuthorName() {
        Assert.assertEquals(bookPage.getBookAuthor(),bookPage.compareAuthorName(bookId));
    }

    @Test(groups = "Smoke", description = "Test that book picture exists")
    public void checkBookPicture() {
        Assert.assertTrue(bookPage.getPicture());
    }

    @Test(groups = "Smoke", description = "Test that book picture exists")
    public void checkBookPrice() {
        Assert.assertEquals(bookPage.getPrice(), "$10.00");
    }

    @Test(groups = "Smoke", description = "Test that add to cart button works")
    public void checkAddToCartButton() {
        Assert.assertTrue(bookPage.addBookToCart());
    }

    @Test(groups = "Smoke", description = "Test that cart has book inside")
    public void checkBookInCart() {
        bookPage.addBookToCart();
        open("cgi-bin/shopping_cart.py");
        Assert.assertEquals(bookPage.getBookNameFromCart(),bookPage.compareBookName(bookId));
    }

    @AfterClass
    public void doLogout(){
        UtilitiesSharelane.logout();
    }





}