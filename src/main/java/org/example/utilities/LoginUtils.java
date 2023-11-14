package org.example.utilities;

import com.codeborne.selenide.SelenideElement;
import org.example.model.UserModel;
import org.example.model.UserModelBuilder;
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

    public static String getLogin() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("src/main/java/org/example/resources/credentials.json"));
        return ((JSONObject) obj).get("login").toString();
    }

    public static String getPassword() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("src/main/java/org/example/resources/credentials.json"));
        return ((JSONObject) obj).get("password").toString();
    }

    public static boolean loginToSite(UserModel user) throws IOException, ParseException {
        //Get login and password values from JSON file
//        JSONParser parser = new JSONParser();
//        String login = getLogin();
//        String password = getPassword();

        //Logging in
        getLoginElement().sendKeys(user.getEmail());
        getPasswordElement().sendKeys(user.getPassword());
        getLoginButton().click();

        //Check if login is successful (True)
        return $x("//a[@href='./log_out.py']").exists();
    }

    public static void loginOverrideExpiration(UserModel user) throws IOException, ParseException {
        if (loginToSite(user)) {
        } else {
            RegistrationUtils.registerNewAccount();
            UserModel newUser = UserModelBuilder.getSimpleUser();
            if (loginToSite(newUser)) {
            } else {
                System.out.println("Login failed");
            }
        }
    }

    public static void logout(){
        $x("//a[@href='./log_out.py']").click();
    }
}
