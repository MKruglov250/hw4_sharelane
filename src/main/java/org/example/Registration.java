package org.example;

import static com.codeborne.selenide.Selenide.*;

public class Registration {

    public static void clickSubmitButton(){
        $x("//input[@value='Continue']").click();
    }

    public static void clickRegisterButton(){
        $x("//input[@value='Register']").click();
    }

    public boolean submitValidZipCode(){
        UtilitiesSharelane.enterCorrectZipCode();
        clickSubmitButton();
        return $x("//input[@value='Register']").exists();
    }

    public void enterValidCredentials(){
        UtilitiesSharelane.enterValidCredentials();
    }

    public boolean submitCredentials(){
        clickRegisterButton();
        return $x("//span[@class='confirmation_message']").exists();
    }

    public boolean isInputFieldHidden(String fieldName){
        return $x("//input[@name='" + fieldName + "']").
                getAttribute("type").equals("password");
    }
}
