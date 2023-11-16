package org.example;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.example.utilities.LoginUtils;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    SelenideElement firstBookImage = $$("img[height='150']").get(0);

    SelenideElement secondBookImage = $$("img[height='150']").get(1);

    SelenideElement searchInputControl = $("input[name='keyword']");

    SelenideElement searchButton = $("input[value='Search']");

    SelenideElement registerLink = $("a[href='./register.py']");

    SelenideElement shoppingCartLink = $("a[href='./shopping_cart.py']");


    @Step("Identifies first book image control")
    public SelenideElement getFirstBookImage(){
        return firstBookImage;
    }

    @Step("Identifies second book image control")
    public SelenideElement getSecondBookImage(){
        return secondBookImage;
    }

    @Step("Identifies search input control")
    public SelenideElement getSearchInputControl(){
        return searchInputControl;
    }

    @Step("Identifies search button")
    public SelenideElement getSearchInputButton(){
        return searchButton;
    }

    @Step("Identifies register navigation link on page")
    public SelenideElement getRegisterLink(){
        return registerLink;
    }

    @Step("Identifies Shopping Cart link on page")
    public SelenideElement getShoppingCartLink(){
        return shoppingCartLink;
    }

    @Step("Identifies Login control on page")
    public SelenideElement getMainPageLogin(){
        return LoginUtils.getLoginElement();
    }

    @Step("Identifies Password control on page")
    public SelenideElement getMainPagePassword(){
        return LoginUtils.getPasswordElement();
    }

    @Step("Identifies Login button on page")
    public SelenideElement getLoginButton(){
        return LoginUtils.getLoginButton();
    }
}
