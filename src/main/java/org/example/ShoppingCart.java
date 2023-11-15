package org.example;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.webdriver;

public class ShoppingCart {

    static ElementsCollection booksTableRows
            = $x("//tr[@class='yellow_bg']").sibling(0).findAll("td");

    SelenideElement bookName = booksTableRows.get(1);

    SelenideElement bookQuantity = $x("//input[@name='q']");

    SelenideElement price = booksTableRows.get(3);

    SelenideElement discount = booksTableRows.get(4);

    SelenideElement totalPrice = booksTableRows.get(6);

    SelenideElement updateButton
            = $x("//input[@value='Update']");

    SelenideElement checkoutButton
            = $x("//input[@value='Proceed to Checkout']");

    public String getBookName(){
        return booksTableRows.get(1).getText();
    }

    public int getQuantity(){
        return Integer.parseInt($x("//input[@name='q']").getValue());
    }

    public int changeQuantity(int i){
        bookQuantity.setValue(String.valueOf(i));
        updateButton.click();
        return Integer.parseInt(bookQuantity.getValue());
    }

    public double getPrice(){
        return Double.parseDouble(price.getText());
    }

    public int getDiscount(){
        return Integer.parseInt(discount.getText());
    }

    public double getTotalPrice(){
        return Double.parseDouble(totalPrice.getText());
    }

    public boolean checkoutPageCheck(){
        checkoutButton.click();
        return webdriver().object().getCurrentUrl().contains("/checkout.py");
    }
}
