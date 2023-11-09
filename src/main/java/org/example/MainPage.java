package org.example;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
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
        return UtilitiesSharelane.getLoginElement();
    }

    public SelenideElement getMainPagePassword(){
        return UtilitiesSharelane.getPasswordElement();
    }

    public SelenideElement getLoginButton(){
        return UtilitiesSharelane.getLoginButton();
    }
}
