package org.example;

import lombok.extern.log4j.Log4j2;
import org.example.model.UserModelBuilder;
import org.example.utilities.LoginUtils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class ShoppingCartTest extends BaseTest{

    BookPage bookPage = new BookPage();
    ShoppingCart shoppingCart = new ShoppingCart();

    @BeforeClass(alwaysRun = true, description = "Open Main page, login, and add book #4 to cart")
    public void openMainPage() throws IOException, ParseException {
        log.info("Starting Shopping Cart test suite");
        open("cgi-bin/main.py");
        LoginUtils.loginOverrideExpiration(UserModelBuilder.getSimpleUser());
        open("cgi-bin/show_book.py?book_id=4");
        bookPage.addBookToCart();
        open("cgi-bin/shopping_cart.py");
        log.info("Book 4 added to shopping cart");
    }

    @Test(groups = "Smoke", description = "Check that book War and Peace is added")
    public void bookIsAddedToCart(){
        Assert.assertEquals(shoppingCart.getBookName(),"War and Peace");
        log.info("Book name checked");
    }

    @Test(groups = "Smoke", description = "Check that quantity is displayed")
    public void checkBookQuantity(){
        Assert.assertEquals(shoppingCart.getQuantity(),1);
        log.info("Default book quantity display checked");
    }

    @Test(groups = "Smoke", description = "Check that Price is 10 bucks")
    public void checkBookPrice(){
        Assert.assertEquals(shoppingCart.getPrice(), 10);
        log.info("Book price checked");
    }

    // Bug for now: actual value is 7%
    @Test(groups = "Smoke", description = "Check that discount is 0%")
    public void checkDiscount(){
        Assert.assertEquals(shoppingCart.getDiscount(),0);
        log.info("Checked that discount for 1 book is 0%");
    }


    @Test(groups = "Smoke", description = "Check that quantity is updated to 25")
    public void checkDiscountUpdated(){
        Assert.assertEquals(shoppingCart.changeQuantity(25),25);
        log.info("Checked changing quantity of books to 25");
    }

    // Bug as of now: incorrect sum of discounts
    @Test(groups = "Smoke", description = "Check that Total Price is Price - discount")
    public void checkTotalPrice(){
        open("cgi-bin/shopping_cart.py?q=25&book_id=10");
        int bookPrice = 10;
        int quantity = 25;
        int discount = shoppingCart.getDiscount();
        double totalPrice = shoppingCart.getTotalPrice();
        Assert.assertEquals(totalPrice, (double) (bookPrice *
                quantity * (100 - discount)) /100);
        log.info("Checked calculation of total price");
    }

    @Test(groups = "Smoke", description = "Test that proceeding to checkout works")
    public void proceedToCheckout(){
        Assert.assertTrue(shoppingCart.checkoutPageCheck());
        log.info("Checked proceeding to checkout page");
    }

    @AfterClass(alwaysRun = true, description = "Log out from website")
    public void doLogout(){
        LoginUtils.logout();
        log.info("Finished Shopping Cart test suite");
    }

}