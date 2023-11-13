package org.example;

import com.codeborne.selenide.SelenideElement;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class CheckoutPage {

    public SelenideElement getCardNumberField(){
        return $x("//input[@name='card_number']");
    }

    public SelenideElement getCardTypeDropdown(){
        return $x("//select[@name='card_type_id']");
    }

    public SelenideElement getPaymentButton(){
        return $x("//input[@value='Make Payment']");
    }

    public void generateNewCards() throws IOException {
        open("cgi-bin/get_credit_card.py");
        $x("//input[@value='Generate Credit Card']").click();
        SelenideElement cardCell = $x("//table[@cellpadding='3']//b");
        JSONObject credJson = new JSONObject();
        credJson.put("visa",cardCell.getText());

        open("cgi-bin/get_credit_card.py");
        $x("//select[@name='type']").selectOption(1);
        $x("//input[@value='Generate Credit Card']").click();
        credJson.put("mastercard",cardCell.getText());

        open("cgi-bin/get_credit_card.py");
        $x("//select[@name='type']").selectOption(2);
        $x("//input[@value='Generate Credit Card']").click();
        credJson.put("amex",cardCell.getText());


        FileWriter file = new FileWriter("utils/creditCard.json");
        file.write(credJson.toJSONString());
        file.flush();
    }

    public String getVisaCard() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("utils/creditCard.json"));
        return ((JSONObject) obj).get("visa").toString();

    }

    public String getMastercardCard() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("utils/creditCard.json"));
        return ((JSONObject) obj).get("mastercard").toString();
    }

    public String getAmexCard() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("utils/creditCard.json"));
        return ((JSONObject) obj).get("amex").toString();
    }

    public String checkVisaBalance() throws IOException, ParseException {
        open("cgi-bin/view_balance.py");
        $x("//input[@name='card_number']").setValue(getVisaCard());
        $x("//input[@value='Show me the balance']").click();
        return $x("//h2").getText().replace("$","");
    }

    public String checkMastercardBalance() throws IOException, ParseException {
        open("cgi-bin/view_balance.py");
        $x("//input[@name='card_number']").setValue(getMastercardCard());
        $x("//input[@value='Show me the balance']").click();
        return $x("//h2").getText().replace("$","");
    }

    public String checkAmexBalance() throws IOException, ParseException {
        open("cgi-bin/view_balance.py");
        $x("//input[@name='card_number']").setValue(getAmexCard());
        $x("//input[@value='Show me the balance']").click();
        return $x("//h2").getText().replace("$","");
    }

    public boolean makePayment(String cardNumber){
        getCardNumberField().setValue(cardNumber);
        getPaymentButton().click();
        return $x("//*[text()='Thank you for your order!!!']").exists();
    }
}
