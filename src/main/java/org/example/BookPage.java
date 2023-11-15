package org.example;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class BookPage {

    SelenideElement bookAuthorCell =
            $x("//table[@width='600']//td[2]//p[1]//b");

    SelenideElement bookNameCell =
            $x("//table[@width='600']//td[2]//p[2]");

    SelenideElement bookPicture =
            $x("//table[@width='600']//img");

    SelenideElement bookPrice =
            $x("//font[@color='green']");

    SelenideElement addToCartButton =
            $x("//img[@src='../images/add_to_cart.gif']");

    public String getBookAuthor(){
        return bookAuthorCell.getText();
    }

    public String getBookName(){
        return bookNameCell.getText();
    }

    public boolean getPicture(){
        return bookPicture.exists();
    }

    public String getPrice(){
        return bookPrice.getText().replace("Price: ", "");
    }

    public boolean addBookToCart(){
        addToCartButton.click();
        return $x("//*[text()='Book was added to the Shopping Cart']")
                .exists();
    }

}
