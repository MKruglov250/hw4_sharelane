package org.example;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.example.utilities.CreditCardUtils;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

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
        return cardNumberField;
    }

    @Step("Get Card Type dropdown element from page")
    public SelenideElement getCardTypeDropdown(){
        return cardTypeDropdown;
    }

    @Step("Get Checkout button from page")
    public SelenideElement getPaymentButton(){
        return paymentButton;
    }

    @Step("Get Visa card from saved card credentials")
    public String getVisaCard() {
        return CreditCardUtils.getVisaCard();
    }

    @Step("Get Mastercard card from saved card credentials")
    public String getMastercardCard() {
        return CreditCardUtils.getMastercardCard();
    }

    @Step("Get Amex card from saved card credentials")
    public String getAmexCard() {
        return CreditCardUtils.getAmexCard();
    }

    @Step("Check balance of Visa card on a simulation website")
    public String checkVisaBalance() {
        open(balancePage);
        cardNumberBalanceInput.setValue(getVisaCard());
        checkBalanceButton.click();
        return cardBalance.getText().replace("$","");
    }

    @Step("Check balance of Mastercard card on a simulation website")
    public String checkMastercardBalance() {
        open(balancePage);
        cardNumberBalanceInput.setValue(getMastercardCard());
        checkBalanceButton.click();
        return cardBalance.getText().replace("$","");
    }

    @Step("Check balance of Amex card on a simulation website")
    public String checkAmexBalance() {
        open(balancePage);
        cardNumberBalanceInput.setValue(getAmexCard());
        checkBalanceButton.click();
        return cardBalance.getText().replace("$","");
    }

    @Step("Check that payment is completed with success message")
    public boolean makePayment(String cardNumber){
        getCardNumberField().setValue(cardNumber);
        getPaymentButton().click();
        return successOrderMessage.exists();
    }
}
