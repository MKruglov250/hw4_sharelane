package org.example;

import lombok.extern.log4j.Log4j2;
import org.example.model.BookModel;
import org.example.model.UserModelBuilder;
import org.example.utilities.JsonUtils;
import org.example.utilities.LoginUtils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;

@Log4j2
public class BookPageTest extends BaseTest {

    BookPage bookPage = new BookPage();
    MainPage mainPage = new MainPage();

    static int bookId;

    static BookModel bookActive;
    static BookModel bookReference;

    @BeforeClass(alwaysRun = true, description = "Log in to website")
    public void doLogin() throws IOException, ParseException {
        log.info("Started Book Page test suite");
        open("cgi-bin/main.py");
        LoginUtils.loginOverrideExpiration(UserModelBuilder.getSimpleUser());
        log.info("Login successful");
    }

    @BeforeClass(alwaysRun = true, description = "Get first book found on Main Page as Active book")
    public void getActiveBookFromMainPage(){
        log.info("Creating Active book from website data");
        open("cgi-bin/main.py");
        mainPage.getFirstBookImage().click();
        bookId = Integer.parseInt(webdriver().object().getCurrentUrl().
                replaceAll("[\\D]", ""));
        bookActive = new BookModel(bookPage.getBookName(),
                bookPage.getBookAuthor(), bookId);
        log.info("Active book created");
    }

    @BeforeClass(alwaysRun = true, description = "Get book from JSON file by id as Reference book")
    public void getReferneceBookFromStorage(){
        log.info("Creating Reference book from JSON storage");
        bookReference = new BookModel(JsonUtils.getBookJson(bookId));
        log.info("Reference book created");
    }

    @Test(groups = "Smoke", description = "Test that book has correct name")
    public void checkBookName() {
        Assert.assertEquals(bookActive.name, bookReference.name);
        log.info("Asserted book name");
    }

    @Test(groups = "Smoke", description = "Test that book author is correct")
    public void checkAuthorName() {
        Assert.assertEquals(bookActive.author, bookReference.author);
        log.info("Asserted book author");
    }

    @Test(groups = "Smoke", description = "Test that book picture exists")
    public void checkBookPicture() {
        Assert.assertTrue(bookPage.getPicture());
        log.info("Asserted that book picture exists");
    }

    @Test(groups = "Smoke", description = "Test that book price exists")
    public void checkBookPrice() {
        Assert.assertEquals(bookPage.getPrice(), "$10.00");
        log.info("Asserted book price");
    }

    @Test(groups = "Smoke", description = "Test that add to cart button works", priority = 1)
    public void checkAddToCartButton() {
        Assert.assertTrue(bookPage.addBookToCart());
        log.info("Asserted that AddToCart button works");
    }

    @AfterClass(alwaysRun = true, description = "Log out from website")
    public void doLogout(){
        LoginUtils.logout();
        log.info("Finished book page suite");
    }





}