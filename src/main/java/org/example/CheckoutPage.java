package org.example;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.example.utilities.CreditCardUtils;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class CheckoutPage {

    SelenideElement cardNumberField = $x("//input[@name='card_number']");

    SelenideElement cardTypeDropdown = $x("//select[@name='card_type_id']");

    SelenideElement paymentButton = $x("//input[@value='Make Payment']");

    String balancePage = "cgi-bin/view_balance.py";

    SelenideElement cardNumberBalanceInput
            = $x("//input[@name='card_number']");

    SelenideElement checkBalanceButton
            = $x("//input[@value='Show me the balance']");

    SelenideElement cardBalance = $x("//h2");

    SelenideElement successOrderMessage
            = $x("//*[text()='Thank you for your order!!!']");

    @Step("Get Card Number field from page")
    public SelenideElement getCardNumberField(){
        log.debug("Getting CardNumber field from Checkout Page");
        return cardNumberField;
    }

    @Step("Get Card Type dropdown element from page")
    public SelenideElement getCardTypeDropdown(){
        log.debug("Getting CardType dropdown from Checkout Page");
        return cardTypeDropdown;
    }

    @Step("Get Checkout button from page")
    public SelenideElement getPaymentButton(){
        log.debug("Getting button for Payment from Checkout Page");
        return paymentButton;
    }

    @Step("Get Visa card from saved card credentials")
    public String getVisaCard() {
        log.debug("Getting Visa card from JSON storage");
        return CreditCardUtils.getVisaCard();
    }

    @Step("Get Mastercard card from saved card credentials")
    public String getMastercardCard() {
        log.debug("Getting Mastercard card from JSON storage");
        return CreditCardUtils.getMastercardCard();
    }

    @Step("Get Amex card from saved card credentials")
    public String getAmexCard() {
        log.debug("Getting Amex card from JSON storage");
        return CreditCardUtils.getAmexCard();
    }

    @Step("Check balance of Visa card on a simulation website")
    public String checkVisaBalance() {
        log.debug("Opening Balance Viewer page");
        open(balancePage);
        log.debug("Entering Visa card data and retrieving balance");
        cardNumberBalanceInput.setValue(getVisaCard());
        checkBalanceButton.click();
        return cardBalance.getText().replace("$","");
    }

    @Step("Check balance of Mastercard card on a simulation website")
    public String checkMastercardBalance() {
        log.debug("Opening Balance Viewer page");
        open(balancePage);
        log.debug("Entering Mastercard card data and retrieving balance");
        cardNumberBalanceInput.setValue(getMastercardCard());
        checkBalanceButton.click();
        return cardBalance.getText().replace("$","");
    }

    @Step("Check balance of Amex card on a simulation website")
    public String checkAmexBalance() {
        log.debug("Opening Balance Viewer page");
        open(balancePage);
        log.debug("Entering Amex card data and retrieving balance");
        cardNumberBalanceInput.setValue(getAmexCard());
        checkBalanceButton.click();
        return cardBalance.getText().replace("$","");
    }

    @Step("Check that payment is completed with success message")
    public boolean makePayment(String cardNumber){
        log.debug("Entering credit card credentials and making payment");
        getCardNumberField().setValue(cardNumber);
        getPaymentButton().click();
        log.debug("Retrieving Order Success message and checking its existence");
        return successOrderMessage.exists();
    }
}
