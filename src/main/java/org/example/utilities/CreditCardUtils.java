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

    public static void generateNewCards() throws IOException {
        open("cgi-bin/get_credit_card.py");
        $x("//input[@value='Generate Credit Card']").click();
        SelenideElement cardCell = $x("//table[@cellpadding='3']//b");
        org.json.simple.JSONObject credJson = new org.json.simple.JSONObject();
        credJson.put("visa",cardCell.getText());

        open("cgi-bin/get_credit_card.py");
        $x("//select[@name='type']").selectOption(1);
        $x("//input[@value='Generate Credit Card']").click();
        credJson.put("mastercard",cardCell.getText());

        open("cgi-bin/get_credit_card.py");
        $x("//select[@name='type']").selectOption(2);
        $x("//input[@value='Generate Credit Card']").click();
        credJson.put("amex",cardCell.getText());

        // Write all three credit cards to Json
        FileWriter file = new FileWriter("src/main/java/org/example/resources/creditCard.json");
        file.write(credJson.toJSONString());
        file.flush();
    }

    public static String getVisaCard() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("src/main/java/org/example/resources/creditCard.json"));
        return ((org.json.simple.JSONObject) obj).get("visa").toString();

    }

    public static String getMastercardCard() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("src/main/java/org/example/resources/creditCard.json"));
        return ((org.json.simple.JSONObject) obj).get("mastercard").toString();
    }

    public static String getAmexCard() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("src/main/java/org/example/resources/creditCard.json"));
        return ((org.json.simple.JSONObject) obj).get("amex").toString();
    }
}
