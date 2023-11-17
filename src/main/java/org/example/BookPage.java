package org.example;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
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

    @Step("Checks author of selected book")
    public String getBookAuthor(){
        var text = bookAuthorCell.getText();
        log.info("Checks author of selected book" + text);
        return text;
    }

    @Step("Checks name of selected book")
    public String getBookName(){
        return bookNameCell.getText();
    }

    @Step("Checks if picture exists for selected book")
    public boolean getPicture(){
        return bookPicture.exists();
    }

    @Step("Checks price of selected book")
    public String getPrice(){
        return bookPrice.getText().replace("Price: ", "");
    }

    @Step("Checks that book was added to cart and success message appeared")
    public boolean addBookToCart(){
        addToCartButton.click();
        return $x("//*[text()='Book was added to the Shopping Cart']")
                .exists();
    }

}
