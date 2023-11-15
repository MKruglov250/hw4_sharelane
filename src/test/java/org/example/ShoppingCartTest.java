package org.example;

import org.example.model.UserModelBuilder;
import org.example.utilities.LoginUtils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

public class ShoppingCartTest extends BaseTest{

    BookPage bookPage = new BookPage();
    ShoppingCart shoppingCart = new ShoppingCart();

    @BeforeClass(alwaysRun = true)
    public void openMainPage() throws IOException, ParseException {
        open("cgi-bin/main.py");
        LoginUtils.loginOverrideExpiration(UserModelBuilder.getSimpleUser());
        open("cgi-bin/show_book.py?book_id=4");
        bookPage.addBookToCart();
        open("cgi-bin/shopping_cart.py");
    }

    @Test(groups = "Smoke", description = "Check that book War and Peace is added")
    public void bookIsAddedToCart(){
        Assert.assertEquals(shoppingCart.getBookName(),"War and Peace");
    }

    @Test(groups = "Smoke", description = "Check that quantity is displayed")
    public void checkBookQuantity(){
        Assert.assertEquals(shoppingCart.getQuantity(),1);
    }

    @Test(groups = "Smoke", description = "Check that Price is 10 bucks")
    public void checkBookPrice(){
        Assert.assertEquals(shoppingCart.getPrice(), 10);
    }

    // Bug for now: actual value is 7%
    @Test(groups = "Smoke", description = "Check that discount is 0%")
    public void checkDiscount(){
        Assert.assertEquals(shoppingCart.getDiscount(),0);
    }


    @Test(groups = "Smoke", description = "Check that quantity is updated to 25")
    public void checkDiscountUpdated(){
        Assert.assertEquals(shoppingCart.changeQuantity(25),25);
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
    }

    @Test(groups = "Smoke", description = "Test that proceeding to checkout works")
    public void proceedToCheckout(){
        Assert.assertTrue(shoppingCart.checkoutPageCheck());
        open("cgi-bin/main.py");
    }

    @AfterClass(alwaysRun = true)
    public void doLogout(){
        LoginUtils.logout();
    }



}