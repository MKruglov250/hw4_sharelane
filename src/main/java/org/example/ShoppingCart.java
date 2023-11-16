package org.example;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

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

    @Step("Gets book name from shopping cart")
    public String getBookName(){
        return bookName.getText();
    }

    @Step("Gets quantity of books in shopping cart")
    public int getQuantity(){
        return Integer.parseInt(bookQuantity.getValue());
    }

    @Step("Changes quantity of books in cart and checks that it got changed")
    public int changeQuantity(int i){
        bookQuantity.setValue(String.valueOf(i));
        updateButton.click();
        return Integer.parseInt(bookQuantity.getValue());
    }

    @Step("Gets price of a book in a cart")
    public double getPrice(){
        return Double.parseDouble(price.getText());
    }

    @Step("Gets discount for a book in a cart")
    public int getDiscount(){
        return Integer.parseInt(discount.getText());
    }

    @Step("Gets total price to pay for book quantity")
    public double getTotalPrice(){
        return Double.parseDouble(totalPrice.getText());
    }

    @Step("Navigates to Checkout page with items in carts and check that checkout page opened")
    public boolean checkoutPageCheck(){
        checkoutButton.click();
        return webdriver().object().getCurrentUrl().contains("/checkout.py");
    }
}
