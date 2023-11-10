package org.example;


import com.codeborne.selenide.SelenideElement;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

import static com.codeborne.selenide.Selenide.*;

public class UtilitiesSharelane {

    public static SelenideElement getLoginElement(){
        return $("input[name='email']");
    }

    public static SelenideElement getPasswordElement(){
        return $("input[name='password']");
    }

    public static SelenideElement getLoginButton(){
        return $("input[value='Login']");
    }

    public static void openRegistrationPage(){
        open("https://www.sharelane.com/cgi-bin/register.py");
    }

    public static void enterCorrectZipCode(){
        $x("//input[@name='zip_code']").sendKeys("33333");
    }

    public static void enterValidCredentials(){
        $x("//input[@name='first_name']").sendKeys("Tester");
        $x("//input[@name='last_name']").sendKeys("Schemer");
        $x("//input[@name='email']").sendKeys("testemail@email.com");
        $x("//input[@name='password1']").sendKeys("1111");
        $x("//input[@name='password2']").sendKeys("1111");
    }

    public static void parseLoginAndPassword() throws IOException {
        String login = $x("//table[@border='1']//tr[1]//td[2]").getText();
        String password = $x("//table[@border='1']//tr[2]//td[2]").getText();

        JSONObject credJson = new JSONObject();
        credJson.put("login",login);
        credJson.put("password",password);

        FileWriter file = new FileWriter("utils/credentials.json");
        file.write(credJson.toJSONString());
        file.flush();
    }


    public static void registerNewAccount() throws IOException {

        //Go to registration page
        openRegistrationPage();

        //Enter correct zip-code
        enterCorrectZipCode();
        $x("//input[@value='Continue']").click();

        //Enter valid credentials and press Submit
        enterValidCredentials();
        $x("//input[@value='Register']").click();

        //Account is created: parsing login data and saving to JSON
        parseLoginAndPassword();
        open("cgi-bin/main.py");
    }

    public static boolean loginToSite() throws IOException, ParseException {
        //Get login and password values from JSON file
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("utils/credentials.json"));
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
            registerNewAccount();
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
