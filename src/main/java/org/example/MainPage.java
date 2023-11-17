package org.example;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.example.utilities.LoginUtils;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class MainPage {

    SelenideElement firstBookImage = $$("img[height='150']").get(0);

    SelenideElement secondBookImage = $$("img[height='150']").get(1);

    SelenideElement searchInputControl = $("input[name='keyword']");

    SelenideElement searchButton = $("input[value='Search']");

    SelenideElement registerLink = $("a[href='./register.py']");

    SelenideElement shoppingCartLink = $("a[href='./shopping_cart.py']");


    @Step("Identifies first book image control")
    public SelenideElement getFirstBookImage(){
        log.debug("Getting first book element from main page");
        return firstBookImage;
    }

    @Step("Identifies second book image control")
    public SelenideElement getSecondBookImage(){
        log.debug("Getting second book element from main page");
        return secondBookImage;
    }

    @Step("Identifies search input control")
    public SelenideElement getSearchInputControl(){
        log.debug("Getting Search Input field from main page");
        return searchInputControl;
    }

    @Step("Identifies search button")
    public SelenideElement getSearchInputButton(){
        log.debug("Getting Search Button from main page");
        return searchButton;
    }

    @Step("Identifies register navigation link on page")
    public SelenideElement getRegisterLink(){
        log.debug("Getting Registration page link from main page");
        return registerLink;
    }

    @Step("Identifies Shopping Cart link on page")
    public SelenideElement getShoppingCartLink(){
        log.debug("Getting Shopping Cart page link from main page");
        return shoppingCartLink;
    }

    @Step("Identifies Login control on page")
    public SelenideElement getMainPageLogin(){
        log.debug("Getting Login (email) input field from Main Page");
        return LoginUtils.getLoginElement();
    }

    @Step("Identifies Password control on page")
    public SelenideElement getMainPagePassword(){
        log.debug("Getting Password input field from main page");
        return LoginUtils.getPasswordElement();
    }

    @Step("Identifies Login button on page")
    public SelenideElement getLoginButton(){
        log.debug("Getting Login button from main page");
        return LoginUtils.getLoginButton();
    }
}
