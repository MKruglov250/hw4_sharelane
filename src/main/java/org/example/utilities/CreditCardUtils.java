package org.example.utilities;

import com.codeborne.selenide.SelenideElement;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class CreditCardUtils {

    static SelenideElement cardCell = $x("//table[@cellpadding='3']//b");

    static JSONParser parser = new JSONParser();
    static Object obj;

    static {
        try {
            obj = parser.parse(new FileReader("src/resources/creditCard.json"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateCreditCard(int cardType){
        open("cgi-bin/get_credit_card.py");
        $x("//select[@name='type']").selectOption(cardType);
        $x("//input[@value='Generate Credit Card']").click();
        return cardCell.getText();
    }

    public static void generateNewCards() throws IOException {
        org.json.simple.JSONObject credJson = new org.json.simple.JSONObject();

        // Generate Visa card
        credJson.put("visa",generateCreditCard(0));

        // Generate Mastercard card
        credJson.put("mastercard",generateCreditCard(1));

        // Generate AmEx card
        credJson.put("amex",generateCreditCard(2));

        // Write all three credit cards to Json file
        FileWriter file = new FileWriter("src/resources/creditCard.json");
        file.write(credJson.toJSONString());
        file.flush();
        try {
            obj = parser.parse(new FileReader("src/resources/creditCard.json"));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getVisaCard() {
        return ((org.json.simple.JSONObject) obj).get("visa").toString();

    }

    public static String getMastercardCard() {
        return ((org.json.simple.JSONObject) obj).get("mastercard").toString();
    }

    public static String getAmexCard() {
        return ((org.json.simple.JSONObject) obj).get("amex").toString();
    }
}
