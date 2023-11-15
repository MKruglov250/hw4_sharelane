package org.example;

import com.codeborne.selenide.SelenideElement;
import org.example.model.UserModel;
import org.example.model.UserModelBuilder;
import org.example.utilities.LoginUtils;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    SelenideElement logoutLink = $x("//a[@href='./log_out.py']");
    SelenideElement failLoginMessage = $x("//span[@class='error_message']");

    public boolean checkLoginToSite(UserModel user) {
        LoginUtils.loginToSite(user);
        return (!failLoginMessage.exists());
    }

    public boolean checkLoginOrRegisterAndLogin() throws IOException, ParseException {
        LoginUtils.loginOverrideExpiration(UserModelBuilder.getSimpleUser());
        return logoutLink.exists();
    }
}
