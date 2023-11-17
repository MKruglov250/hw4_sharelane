package org.example;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.webdriver;

@Log4j2
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
        log.debug("Getting Book Name from Shopping Cart");
        return bookName.getText();
    }

    @Step("Gets quantity of books in shopping cart")
    public int getQuantity(){
        log.debug("Getting current quantity of books from Shopping Cart");
        return Integer.parseInt(bookQuantity.getValue());
    }

    @Step("Changes quantity of books in cart and checks that it got changed")
    public int changeQuantity(int i){
        log.debug("Setting book quantity to " + String.valueOf(i) + " and pressing Update");
        bookQuantity.setValue(String.valueOf(i));
        updateButton.click();
        log.debug("Getting new quantity of books in Shopping Cart");
        return Integer.parseInt(bookQuantity.getValue());
    }

    @Step("Gets price of a book in a cart")
    public double getPrice(){
        log.debug("Getting Book Price from Shopping Cart");
        return Double.parseDouble(price.getText());
    }

    @Step("Gets discount for a book in a cart")
    public int getDiscount(){
        log.debug("Getting Discount for current book from Shopping Cart");
        return Integer.parseInt(discount.getText());
    }

    @Step("Gets total price to pay for book quantity")
    public double getTotalPrice(){
        log.debug("Getting current Total Price to pay from Shopping Cart");
        return Double.parseDouble(totalPrice.getText());
    }

    @Step("Navigates to Checkout page with items in carts and check that checkout page opened")
    public boolean checkoutPageCheck(){
        log.debug("Checking out by clicking Checkout button");
        checkoutButton.click();
        return webdriver().object().getCurrentUrl().contains("/checkout.py");
    }
}
