package org.example;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$x;

public class ShoppingCart {

    static ElementsCollection booksTableRows = $x("//tr[@class='yellow_bg']").sibling(0).findAll("td");

    public String getBookName(){
        return booksTableRows.get(1).getText();
    }

    public int getQuantity(){
        return Integer.parseInt($x("//input[@name='q']").getValue());
    }

    public int changeQuantity(int i){
        $x("//input[@name='q']").setValue(String.valueOf(i));
        $x("//input[@value='Update']").click();
        return Integer.parseInt($x("//input[@name='q']").getValue());
    }

    public double getPrice(){
        return Double.parseDouble(booksTableRows.get(3).getText());
    }

    public int getDiscount(){
        return Integer.parseInt(booksTableRows.get(4).getText());
    }

    public double getTotalPrice(){
        return Double.parseDouble(booksTableRows.get(6).getText());
    }

    public boolean checkoutPageCheck(){
        $x("//input[@value='Proceed to Checkout']").click();
        return $x("//input[@name='card_number']").exists();
    }
}
