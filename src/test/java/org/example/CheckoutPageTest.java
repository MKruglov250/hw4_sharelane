package org.example;

import com.codeborne.selenide.SelenideElement;
import org.example.model.UserModelBuilder;
import org.example.utilities.CreditCardUtils;
import org.example.utilities.LoginUtils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class CheckoutPageTest extends BaseTest {

    BookPage bookPage = new BookPage();
    CheckoutPage checkoutPage = new CheckoutPage();
    MainPage mainPage = new MainPage();
    SelenideElement checkoutButton = $x("//input[@value='Proceed to Checkout']");
    SelenideElement shoppingCart = $x("//a[@href='./shopping_cart.py']");


    @BeforeClass(alwaysRun = true)
    public void generateTestCards() throws IOException {
        CreditCardUtils.generateNewCards();
    }

    @BeforeMethod(alwaysRun = true)
    public void addBookToCartAndCheckout() throws IOException, ParseException {
        open("cgi-bin/main.py");
        LoginUtils.loginOverrideExpiration(UserModelBuilder.getSimpleUser());
        mainPage.getFirstBookImage().click();
        bookPage.addBookToCart();
        shoppingCart.click();
        checkoutButton.click();
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
    public void checkValidVisaCredentials() {
        checkoutPage.getCardTypeDropdown().selectOption(0);
        Assert.assertTrue(checkoutPage.makePayment(checkoutPage.getVisaCard()));
    }

    @Test(groups = "Smoke", description = "Check Valid Mastercard credentials payment")
    public void checkValidMastercardCredentials() {
        checkoutPage.getCardTypeDropdown().selectOption(1);
        Assert.assertTrue(checkoutPage.makePayment(checkoutPage.getMastercardCard()));
    }

    @Test(groups = "Smoke", description = "Check Valid Amex credentials payment")
    public void checkValidAmexCredentials() {
        checkoutPage.getCardTypeDropdown().selectOption(2);
        Assert.assertTrue(checkoutPage.makePayment(checkoutPage.getAmexCard()));
    }


    // All 3 tests below fail because of website bugs
    @Test(groups = "Smoke", description = "Check Visa card balance after payment",
            priority = 1)
    public void checkVisaBalanceAfterPayment() {
        Assert.assertEquals("990.00", checkoutPage.checkVisaBalance());
    }

    @Test(groups = "Smoke", description = "Check Mastercard card balance after payment",
            priority = 1)
    public void checkMastercardBalanceAfterPayment() {
        Assert.assertEquals("990.00", checkoutPage.checkMastercardBalance());
    }

    @Test(groups = "Smoke", description = "Check Amex card balance after payment",
            priority = 1)
    public void checkAmexBalanceAfterPayment() {
        Assert.assertEquals("990.00", checkoutPage.checkAmexBalance());
    }

    @AfterMethod(alwaysRun = true, description = "Log out from website")
    public void doLogout(){
        LoginUtils.logout();
    }

}