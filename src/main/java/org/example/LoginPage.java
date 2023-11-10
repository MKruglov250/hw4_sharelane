package org.example;

import org.example.model.UserModel;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    public boolean checkLoginOrRegisterAndLogin() throws IOException, ParseException {
        UtilitiesSharelane.loginOverrideExpiration();
        return $x("//a[@href='./log_out.py']").exists();
    }

    public void login(UserModel user) {
    }
}
