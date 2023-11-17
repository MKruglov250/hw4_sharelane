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
        log.debug("Getting book author as text from element");
        return bookAuthorCell.getText();
    }

    @Step("Checks name of selected book")
    public String getBookName(){
        log.debug("Getting book name as text from element");
        return bookNameCell.getText();
    }

    @Step("Checks if picture exists for selected book")
    public boolean getPicture(){
        log.debug("Getting book picture from element and checking existence");
        return bookPicture.exists();
    }

    @Step("Checks price of selected book")
    public String getPrice(){
        log.debug("Getting book price as text");
        return bookPrice.getText().replace("Price: ", "");
    }

    @Step("Checks that book was added to cart and success message appeared")
    public boolean addBookToCart(){
        log.debug("Clicking Add To Cart and checking 'Book was added' message");
        addToCartButton.click();
        return $x("//*[text()='Book was added to the Shopping Cart']")
                .exists();
    }

}
