package org.example.Utilities;

import com.codeborne.selenide.SelenideElement;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginUtils {

    static SelenideElement loginElement = $("input[name='email']");

    static SelenideElement passwordElement = $("input[name='password']");

    static SelenideElement loginButton = $("input[value='Login']");

    public static SelenideElement getLoginElement(){
        return loginElement;
    }

    public static SelenideElement getPasswordElement(){
        return passwordElement;
    }

    public static SelenideElement getLoginButton(){
        return loginButton;
    }

    public static boolean loginToSite() throws IOException, ParseException {
        //Get login and password values from JSON file
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("src/main/java/org/example/resources/credentials.json"));
        String login = ((JSONObject) obj).get("login").toString();
        String password = ((JSONObject) obj).get("password").toString();

        //Logging in
        getLoginElement().sendKeys(login);
        getPasswordElement().sendKeys(password);
        getLoginButton().click();

        //Check if login is successful (True)
        return $x("//a[@href='./log_out.py']").exists();
    }

    public static void loginOverrideExpiration() throws IOException, ParseException {
        if (loginToSite()) {
        } else {
            RegistrationUtils.registerNewAccount();
            if (loginToSite()) {
            } else {
                System.out.println("Login failed");
            }
        }
    }

    public static void logout(){
        $x("//a[@href='./log_out.py']").click();
    }
}
