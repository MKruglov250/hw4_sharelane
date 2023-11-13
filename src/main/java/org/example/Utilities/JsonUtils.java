package org.example.Utilities;

//import org.json.simple.JSONArray;
//import org.json.simple.parser.JSONParser;
import com.codeborne.selenide.SelenideElement;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class JsonUtils {

    public static String getBookName(int i) throws IOException, ParseException {
        JSONArray jsonArray = new JSONArray();

        JSONObject book = (JSONObject) jsonArray.get(i-1);
        return book.get("name").toString();
    }

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


        FileWriter file = new FileWriter("utils/creditCard.json");
        file.write(credJson.toJSONString());
        file.flush();
    }

    public static String getVisaCard() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("utils/creditCard.json"));
        return ((org.json.simple.JSONObject) obj).get("visa").toString();

    }

    public static String getMastercardCard() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("utils/creditCard.json"));
        return ((org.json.simple.JSONObject) obj).get("mastercard").toString();
    }

    public static String getAmexCard() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("utils/creditCard.json"));
        return ((org.json.simple.JSONObject) obj).get("amex").toString();
    }

}
