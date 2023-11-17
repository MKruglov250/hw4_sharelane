package org.example;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.example.model.UserModel;
import org.example.model.UserModelBuilder;
import org.example.utilities.LoginUtils;
import org.json.simple.parser.ParseException;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class LoginPage {

    SelenideElement logoutLink = $x("//a[@href='./log_out.py']");
    SelenideElement failLoginMessage = $x("//span[@class='error_message']");

    @Step("Log in to site")
    public boolean checkLoginToSite(UserModel user) {
        log.debug("Logging into website with " + user.getEmail() + " user email");
        LoginUtils.loginToSite(user);
        log.debug("Checking that there is no Login Failed message on page");
        return (!failLoginMessage.exists());
    }

    @Step("Log in to site and, if failed, register new account and log in once again")
    public boolean checkLoginOrRegisterAndLogin() throws IOException, ParseException {
        log.debug("Login into Website");
        LoginUtils.loginOverrideExpiration(UserModelBuilder.getSimpleUser());
        return logoutLink.exists();
    }
}
