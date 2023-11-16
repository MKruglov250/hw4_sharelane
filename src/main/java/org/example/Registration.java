package org.example;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.example.utilities.RegistrationUtils;

import static com.codeborne.selenide.Selenide.*;

public class Registration {

    static SelenideElement submitButton = $x("//input[@value='Continue']");

    static SelenideElement registerButton = $x("//input[@value='Register']");

    SelenideElement registrationConfirmMessage =
            $x("//span[@class='confirmation_message']");


    @Step("Submits valid zip-code and checks that it is accepted")
    public boolean submitValidZipCode(){
        RegistrationUtils.enterCorrectZipCode();
        submitButton.click();
        return registerButton.exists();
    }

    @Step("Submits valid credentials to registration fields")
    public void enterValidCredentials(){
        RegistrationUtils.enterValidCredentials();
    }

    @Step("Submits credentials entered in Registration field")
    public boolean submitCredentials(){
        registerButton.click();
        return registrationConfirmMessage.exists();
    }

    @Step("Checks if selected {fieldName} is masked or not")
    public boolean isInputFieldHidden(String fieldName){
        return $x("//input[@name='" + fieldName + "']").
                getAttribute("type").equals("password");
    }
}
