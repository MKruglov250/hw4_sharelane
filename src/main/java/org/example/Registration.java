package org.example;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.example.utilities.RegistrationUtils;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class Registration {

    static SelenideElement submitButton = $x("//input[@value='Continue']");

    static SelenideElement registerButton = $x("//input[@value='Register']");

    SelenideElement registrationConfirmMessage =
            $x("//span[@class='confirmation_message']");


    @Step("Submits valid zip-code and checks that it is accepted")
    public boolean submitValidZipCode(){
        log.debug("Entering corret 5-digit zipcode and proceeding with registration");
        RegistrationUtils.enterCorrectZipCode();
        submitButton.click();
        log.debug("Checking that next page opened, and Register button exists on it");
        return registerButton.exists();
    }

    @Step("Submits valid credentials to registration fields")
    public void enterValidCredentials(){
        log.debug("Entering Valid credentials from JSON storage to Registration field");
        RegistrationUtils.enterValidCredentials();
    }

    @Step("Submits credentials entered in Registration field")
    public boolean submitCredentials(){
        log.debug("Pressing Register button, submitting credentials given to input fields");
        registerButton.click();
        return registrationConfirmMessage.exists();
    }

    @Step("Checks if selected {fieldName} is masked or not")
    public boolean isInputFieldHidden(String fieldName){
        log.debug("Checking that " + fieldName + " is masked");
        return $x("//input[@name='" + fieldName + "']").
                getAttribute("type").equals("password");
    }
}
