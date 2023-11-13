package org.example;

import com.codeborne.selenide.SelenideElement;
import org.example.Utilities.JsonUtils;
import org.json.simple.parser.ParseException;
import java.io.IOException;

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

    public SelenideElement getCardNumberField(){
        return cardNumberField;
    }

    public SelenideElement getCardTypeDropdown(){
        return cardTypeDropdown;
    }

    public SelenideElement getPaymentButton(){
        return paymentButton;
    }

    public String getVisaCard() throws IOException, ParseException {
        return JsonUtils.getVisaCard();
    }

    public String getMastercardCard() throws IOException, ParseException {
        return JsonUtils.getMastercardCard();
    }

    public String getAmexCard() throws IOException, ParseException {
        return JsonUtils.getAmexCard();
    }

    public String checkVisaBalance() throws IOException, ParseException {
        open(balancePage);
        cardNumberBalanceInput.setValue(getVisaCard());
        checkBalanceButton.click();
        return cardBalance.getText().replace("$","");
    }

    public String checkMastercardBalance() throws IOException, ParseException {
        open(balancePage);
        cardNumberBalanceInput.setValue(getMastercardCard());
        checkBalanceButton.click();
        return cardBalance.getText().replace("$","");
    }

    public String checkAmexBalance() throws IOException, ParseException {
        open(balancePage);
        cardNumberBalanceInput.setValue(getAmexCard());
        checkBalanceButton.click();
        return cardBalance.getText().replace("$","");
    }

    public boolean makePayment(String cardNumber){
        getCardNumberField().setValue(cardNumber);
        getPaymentButton().click();
        return successOrderMessage.exists();
    }
}
