package org.example;

import org.example.Utilities.JsonUtils;
import org.example.Utilities.LoginUtils;
import org.example.Utilities.RegistrationUtils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class CheckoutPageTest extends BaseTest {

    BookPage bookPage = new BookPage();
    CheckoutPage checkoutPage = new CheckoutPage();


    @BeforeClass
    public void openMainPage() throws IOException, ParseException {
        JsonUtils.generateNewCards();
        open("cgi-bin/main.py");
        LoginUtils.loginOverrideExpiration();
        open("cgi-bin/show_book.py?book_id=4");
        bookPage.addBookToCart();
        open("cgi-bin/shopping_cart.py");
        $x("//input[@value='Proceed to Checkout']").click();
    }

    @BeforeMethod
    public void openCheckoutPage(){
        open("cgi-bin/checkout.py?book_id=4&q=1&total=1070.0");
    }

    @Test(groups = "Smoke",description = "Check card number element on page")
    public void checkCardNumberField(){
        Assert.assertTrue(checkoutPage.getCardNumberField().exists());
    }

    @Test(groups = "Smoke",description = "Check card type dropdown")
    public void checkCardsDropdown(){
        Assert.assertTrue(checkoutPage.getCardTypeDropdown().exists());
    }

    @Test(groups = "Smoke",description = "Check Make Payment button presence")
    public void checkMakePaymentButtonExists(){
        Assert.assertTrue(checkoutPage.getPaymentButton().exists());
    }

    @Test(groups = "Regression", description = "Check invalid Visa credentials payment")
    public void checkInvalidVisaCredentials(){
        checkoutPage.getCardTypeDropdown().selectOption(0);
        Assert.assertFalse(checkoutPage.makePayment("1235321252135212"));
    }

    @Test(groups = "Regression", description = "Check invalid Mastercard credentials payment")
    public void checkInvalidMastercardCredentials(){
        checkoutPage.getCardTypeDropdown().selectOption(1);
        Assert.assertFalse(checkoutPage.makePayment("1235321252135212"));
    }

    @Test(groups = "Regression", description = "Check invalid Amex credentials payment")
    public void checkInvalidAmexCredentials(){
        checkoutPage.getCardTypeDropdown().selectOption(2);
        Assert.assertFalse(checkoutPage.makePayment("1235321252135212"));
    }

    @Test(groups = "Smoke", description = "Check Valid Visa credentials payment")
    public void checkValidVisaCredentials() throws IOException, ParseException {
        checkoutPage.getCardTypeDropdown().selectOption(0);
        Assert.assertTrue(checkoutPage.makePayment(checkoutPage.getVisaCard()));
    }

    @Test(groups = "Smoke", description = "Check Valid Mastercard credentials payment")
    public void checkValidMastercardCredentials() throws IOException, ParseException {
        checkoutPage.getCardTypeDropdown().selectOption(0);
        Assert.assertTrue(checkoutPage.makePayment(checkoutPage.getMastercardCard()));
    }

    @Test(groups = "Smoke", description = "Check Valid Amex credentials payment")
    public void checkValidAmexCredentials() throws IOException, ParseException {
        checkoutPage.getCardTypeDropdown().selectOption(0);
        Assert.assertTrue(checkoutPage.makePayment(checkoutPage.getAmexCard()));
    }


    // All 3 tests below fail because of website bugs
    @Test(groups = "Smoke", description = "Check Visa card balance after payment",
            priority = 1)
    public void checkVisaBalanceAfterPayment() throws IOException, ParseException {
        Assert.assertEquals("990.00", checkoutPage.checkVisaBalance());
    }

    @Test(groups = "Smoke", description = "Check Mastercard card balance after payment",
            priority = 1)
    public void checkMastercardBalanceAfterPayment() throws IOException, ParseException {
        Assert.assertEquals("990.00", checkoutPage.checkMastercardBalance());
    }

    @Test(groups = "Smoke", description = "Check Amex card balance after payment",
            priority = 1)
    public void checkAmexBalanceAfterPayment() throws IOException, ParseException {
        Assert.assertEquals("990.00", checkoutPage.checkAmexBalance());
    }

    @AfterClass
    public void doLogout(){
        open("cgi-bin/main.py");
        LoginUtils.logout();
    }

}