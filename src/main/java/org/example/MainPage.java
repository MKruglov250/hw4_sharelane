package org.example;

import com.codeborne.selenide.SelenideElement;
import org.example.Utilities.LoginUtils;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    SelenideElement firstBookImage = $$("img[height='150']").get(0);

    SelenideElement secondBookImage = $$("img[height='150']").get(1);

    SelenideElement searchInputControl = $("input[name='keyword']");

    SelenideElement searchButton = $("input[value='Search']");

    SelenideElement registerLink = $("a[href='./register.py']");

    SelenideElement shoppingCartLink = $("a[href='./shopping_cart.py']");


    public SelenideElement getFirstBookImage(){
        return $$("img[height='150']").get(0);
    }

    public SelenideElement getSecondBookImage(){
        return $$("img[height='150']").get(1);
    }

    public SelenideElement getSearchInputControl(){
        return $("input[name='keyword']");
    }

    public SelenideElement getSearchInputButton(){
        return $("input[value='Search']");
    }

    public SelenideElement getRegisterLink(){
        return $("a[href='./register.py']");
    }

    public SelenideElement getShoppingCartLink(){
        return $("a[href='./shopping_cart.py']");
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
