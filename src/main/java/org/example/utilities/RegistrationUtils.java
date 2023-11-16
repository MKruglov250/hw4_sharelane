package org.example.utilities;

import io.qameta.allure.Step;

import java.io.*;

import static com.codeborne.selenide.Selenide.*;

public class RegistrationUtils {

    @Step("Open Registration page")
    public static void openRegistrationPage(){
        open("cgi-bin/register.py");
    }

    @Step("Enter zip-code '33333' to pass registration")
    public static void enterCorrectZipCode(){
        $x("//input[@name='zip_code']").sendKeys("33333");
    }

    @Step("Enter valid credentials to pass registration")
    public static void enterValidCredentials(){
        $x("//input[@name='first_name']").sendKeys("Tester");
        $x("//input[@name='last_name']").sendKeys("Schemer");
        $x("//input[@name='email']").sendKeys("testemail@email.com");
        $x("//input[@name='password1']").sendKeys("1111");
        $x("//input[@name='password2']").sendKeys("1111");
    }

    @Step("Get actual Login and Password provided by Sharelane after registration")
    public static void parseLoginAndPassword() throws IOException {
        String login = $x("//table[@border='1']//tr[1]//td[2]").getText();
        String password = $x("//table[@border='1']//tr[2]//td[2]").getText();

        org.json.simple.JSONObject credJson = new org.json.simple.JSONObject();
        credJson.put("login",login);
        credJson.put("password",password);

        FileWriter file = new FileWriter("src/resources/credentials.json");
        file.write(credJson.toJSONString());
        file.flush();
    }


    @Step("Register new account with valid zip-code and credentials")
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

}
