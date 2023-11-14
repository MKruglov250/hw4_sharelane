package org.example;

import com.codeborne.selenide.SelenideElement;
import org.example.utilities.LoginUtils;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    SelenideElement firstBookImage = $$("img[height='150']").get(0);

    SelenideElement secondBookImage = $$("img[height='150']").get(1);

    SelenideElement searchInputControl = $("input[name='keyword']");

    SelenideElement searchButton = $("input[value='Search']");

    SelenideElement registerLink = $("a[href='./register.py']");

    SelenideElement shoppingCartLink = $("a[href='./shopping_cart.py']");


    public SelenideElement getFirstBookImage(){
        return firstBookImage;
    }

    public SelenideElement getSecondBookImage(){
        return secondBookImage;
    }

    public SelenideElement getSearchInputControl(){
        return searchInputControl;
    }

    public SelenideElement getSearchInputButton(){
        return searchButton;
    }

    public SelenideElement getRegisterLink(){
        return registerLink;
    }

    public SelenideElement getShoppingCartLink(){
        return shoppingCartLink;
    }

    public SelenideElement getMainPageLogin(){
        return LoginUtils.getLoginElement();
    }

    public SelenideElement getMainPagePassword(){
        return LoginUtils.getPasswordElement();
    }

    public SelenideElement getLoginButton(){
        return LoginUtils.getLoginButton();
    }
}
