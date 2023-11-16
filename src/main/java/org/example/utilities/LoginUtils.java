package org.example.utilities;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.example.model.UserModel;
import org.example.model.UserModelBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;

public class LoginUtils {

    static SelenideElement loginElement = $("input[name='email']");

    static SelenideElement passwordElement = $("input[name='password']");

    static SelenideElement loginButton = $("input[value='Login']");

    static SelenideElement logoutButton = $("a[href='./log_out.py']");

    @Step("Get Login input field from current page")
    public static SelenideElement getLoginElement(){
        return loginElement;
    }

    @Step("Get Login Password input field from current page")
    public static SelenideElement getPasswordElement(){
        return passwordElement;
    }

    @Step("Get Login Button element from current page")
    public static SelenideElement getLoginButton(){
        return loginButton;
    }

    @Step("Get Login from local Credentials storage")
    public static String getLogin() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("src/resources/credentials.json"));
        return ((JSONObject) obj).get("login").toString();
    }

    @Step("Get Password from local Credentials storage")
    public static String getPassword() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("src/resources/credentials.json"));
        return ((JSONObject) obj).get("password").toString();
    }

    @Step("Logs in to site using credentials of selected {user}")
    public static boolean loginToSite(UserModel user) {

        //Logging in
        getLoginElement().sendKeys(user.getEmail());
        getPasswordElement().sendKeys(user.getPassword());
        getLoginButton().click();

        //Check if login is successful (True)
        return $x("//a[@href='./log_out.py']").exists();
    }

    @Step("Register new account and log in second time if credentials in storage has expired")
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

    @Step("Log out from website")
    public static void logout(){
        open("cgi-bin/main.py");
        if (logoutButton.exists()){
            logoutButton.click();
        }
    }
}
