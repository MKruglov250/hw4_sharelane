package org.example;

import org.example.model.BookModel;
import org.example.model.UserModelBuilder;
import org.example.utilities.JsonUtils;
import org.example.utilities.LoginUtils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;

@Listeners({TestListener.class})
public class BookPageTest extends BaseTest {

    BookPage bookPage = new BookPage();
    MainPage mainPage = new MainPage();

    static int bookId;

    static BookModel bookActive;
    static BookModel bookReference;


    @BeforeClass(alwaysRun = true, description = "Log in to website")
    public void doLogin() throws IOException, ParseException {
        open("cgi-bin/main.py");
        LoginUtils.loginOverrideExpiration(UserModelBuilder.getSimpleUser());
    }

    @BeforeClass(alwaysRun = true, description = "Get first book found on Main Page as Active book")
    public void getActiveBookFromMainPage(){
        open("cgi-bin/main.py");
        mainPage.getFirstBookImage().click();
        bookId = Integer.parseInt(webdriver().object().getCurrentUrl().
                replaceAll("[\\D]", ""));
        bookActive = new BookModel(bookPage.getBookName(),
                bookPage.getBookAuthor(), bookId);
    }

    @BeforeClass(alwaysRun = true, description = "Get book from JSON file by id as Reference book")
    public void getReferneceBookFromStorage(){
        bookReference = new BookModel(JsonUtils.getBookJson(bookId));
    }

    @Test(groups = "Smoke", description = "Test that book has correct name")
    public void checkBookName() {
        Assert.assertEquals(bookActive.name, bookReference.name);
    }

    @Test(groups = "Smoke", description = "Test that book author is correct")
    public void checkAuthorName() {
        Assert.assertEquals(bookActive.author, bookReference.author);
    }

    @Test(groups = "Smoke", description = "Test that book picture exists")
    public void checkBookPicture() {
        Assert.assertTrue(bookPage.getPicture());
    }

    @Test(groups = "Smoke", description = "Test that book price exists")
    public void checkBookPrice() {
        Assert.assertEquals(bookPage.getPrice(), "$10.00");
    }

    @Test(groups = "Smoke", description = "Test that add to cart button works", priority = 1)
    public void checkAddToCartButton() {
        Assert.assertTrue(bookPage.addBookToCart());
        open("cgi-bin/main.py");
    }

    @AfterClass(alwaysRun = true, description = "Log out from website")
    public void doLogout(){
        LoginUtils.logout();
    }





}