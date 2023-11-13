package org.example;

import com.codeborne.selenide.SelenideElement;
import org.example.Utilities.LoginUtils;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    SelenideElement logoutLink = $x("//a[@href='./log_out.py']");

    public boolean checkLoginOrRegisterAndLogin() throws IOException, ParseException {
        LoginUtils.loginOverrideExpiration();
        return logoutLink.exists();
    }
}
