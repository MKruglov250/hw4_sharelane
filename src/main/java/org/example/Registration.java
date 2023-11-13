package org.example;

import com.codeborne.selenide.SelenideElement;
import org.example.Utilities.UtilitiesSharelane;

import static com.codeborne.selenide.Selenide.*;

public class Registration {

    static SelenideElement submitButton = $x("//input[@value='Continue']");

    static SelenideElement registerButton = $x("//input[@value='Register']");

    SelenideElement registrationConfirmMessage =
            $x("//span[@class='confirmation_message']");


    public boolean submitValidZipCode(){
        UtilitiesSharelane.enterCorrectZipCode();
        submitButton.click();
        return registerButton.exists();
    }

    public void enterValidCredentials(){
        UtilitiesSharelane.enterValidCredentials();
    }

    public boolean submitCredentials(){
        registerButton.click();
        return registrationConfirmMessage.exists();
    }

    public boolean isInputFieldHidden(String fieldName){
        return $x("//input[@name='" + fieldName + "']").
                getAttribute("type").equals("password");
    }
}
