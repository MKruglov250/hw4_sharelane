package org.example;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.example.model.UserModelBuilder;
import org.example.utilities.CreditCardUtils;
import org.example.utilities.LoginUtils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class CheckoutPageTest extends BaseTest {

    BookPage bookPage = new BookPage();
    CheckoutPage checkoutPage = new CheckoutPage();
    MainPage mainPage = new MainPage();
    SelenideElement checkoutButton = $x("//input[@value='Proceed to Checkout']");
    SelenideElement shoppingCart = $x("//a[@href='./shopping_cart.py']");


    @BeforeClass(alwaysRun = true)
    public void generateTestCards() throws IOException {
        log.info("Started Checkout Page test suite");
        CreditCardUtils.generateNewCards();
        log.info("Cards generation complete");
    }

    @BeforeMethod(alwaysRun = true)
    public void addBookToCartAndCheckout() throws IOException, ParseException {
        open("cgi-bin/main.py");
        LoginUtils.loginOverrideExpiration(UserModelBuilder.getSimpleUser());
        mainPage.getFirstBookImage().click();
        bookPage.addBookToCart();
        shoppingCart.click();
        checkoutButton.click();
        log.info("Book added to cart");
    }

    @Test(groups = "Smoke", description = "Check card number element on page")
    public void checkCardNumberField() {
        Assert.assertTrue(checkoutPage.getCardNumberField().exists());
        log.info("Card Number field existence asserted");
    }

    @Test(groups = "Smoke", description = "Check card type dropdown")
    public void checkCardsDropdown() {
        Assert.assertTrue(checkoutPage.getCardTypeDropdown().exists());
        log.info("Card type dropdown field existence asserted");
    }

    @Test(groups = "Smoke", description = "Check Make Payment button presence")
    public void checkMakePaymentButtonExists() {
        Assert.assertTrue(checkoutPage.getPaymentButton().exists());
        log.info("Payment button existence asserted");
    }

    @Test(groups = "Regression", description = "Check invalid Visa credentials payment")
    public void checkInvalidVisaCredentials() {
        checkoutPage.getCardTypeDropdown().selectOption(0);
        Assert.assertFalse(checkoutPage.makePayment("1235321252135212"));
        log.info("Payment with invalid Visa card checked");
    }

    @Test(groups = "Regression", description = "Check invalid Mastercard credentials payment")
    public void checkInvalidMastercardCredentials() {
        checkoutPage.getCardTypeDropdown().selectOption(1);
        Assert.assertFalse(checkoutPage.makePayment("1235321252135212"));
        log.info("Payment with invalid Mastercard card checked");
    }

    @Test(groups = "Regression", description = "Check invalid Amex credentials payment")
    public void checkInvalidAmexCredentials() {
        checkoutPage.getCardTypeDropdown().selectOption(2);
        Assert.assertFalse(checkoutPage.makePayment("1235321252135212"));
        log.info("Payment with invalid AmeEx card checked");
    }

    @Test(groups = "Smoke", description = "Check Valid Visa credentials payment")
    public void checkValidVisaCredentials() {
        checkoutPage.getCardTypeDropdown().selectOption(0);
        Assert.assertTrue(checkoutPage.makePayment(checkoutPage.getVisaCard()));
        log.info("Payment with valid Visa card checked");
    }

    @Test(groups = "Smoke", description = "Check Valid Mastercard credentials payment")
    public void checkValidMastercardCredentials() {
        checkoutPage.getCardTypeDropdown().selectOption(1);
        Assert.assertTrue(checkoutPage.makePayment(checkoutPage.getMastercardCard()));
        log.info("Payment with valid Mastercard card checked");
    }

    @Test(groups = "Smoke", description = "Check Valid Amex credentials payment")
    public void checkValidAmexCredentials() {
        checkoutPage.getCardTypeDropdown().selectOption(2);
        Assert.assertTrue(checkoutPage.makePayment(checkoutPage.getAmexCard()));
        log.info("Payment with valid Amex card checked");
    }


    // All 3 tests below fail because of website bugs
    @Test(groups = "Smoke", description = "Check Visa card balance after payment",
            priority = 1)
    public void checkVisaBalanceAfterPayment() {
        Assert.assertEquals(checkoutPage.checkVisaBalance(), "990.00");
        log.info("Asserted Visa card balance after payment equals 990.00");
    }

    @Test(groups = "Smoke", description = "Check Mastercard card balance after payment",
            priority = 1)
    public void checkMastercardBalanceAfterPayment() {
        Assert.assertEquals(checkoutPage.checkMastercardBalance(), "990.00");
        log.info("Asserted Mastercard card balance after payment equals 990.00");
    }

    @Test(groups = "Smoke", description = "Check Amex card balance after payment",
            priority = 1)
    public void checkAmexBalanceAfterPayment() {
        Assert.assertEquals(checkoutPage.checkAmexBalance(),"990.00");
        log.info("Asserted Amex card balance after payment equals 990.00");
    }

    @AfterMethod(alwaysRun = true, description = "Log out from website")
    public void doLogout() {
        LoginUtils.logout();
    }

    @AfterClass(alwaysRun = true)
    public void logFinish(){
        log.info("Finished Checkout Page test suite");
    }

}