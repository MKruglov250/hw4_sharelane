package org.example;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.example.model.UserModel;
import org.example.model.UserModelBuilder;
import org.example.utilities.LoginUtils;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    SelenideElement logoutLink = $x("//a[@href='./log_out.py']");
    SelenideElement failLoginMessage = $x("//span[@class='error_message']");

    @Step("Log in to site")
    public boolean checkLoginToSite(UserModel user) {
        LoginUtils.loginToSite(user);
        return (!failLoginMessage.exists());
    }

    @Step("Log in to site and, if failed, register new account and log in once again")
    public boolean checkLoginOrRegisterAndLogin() throws IOException, ParseException {
        LoginUtils.loginOverrideExpiration(UserModelBuilder.getSimpleUser());
        return logoutLink.exists();
    }
}
