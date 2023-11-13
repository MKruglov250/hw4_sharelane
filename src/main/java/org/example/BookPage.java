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

    public String compareBookName(int i) {
        String bookName = "";
        switch (i) {
            case 1:
                bookName = "Great Expectations";
                break;
            case 2:
                bookName = "White Fang";
                break;
            case 3:
                bookName = "The Adventures of Tom Sawyer";
                break;
            case 4:
                bookName = "War and Peace";
                break;
            case 5:
                bookName = "The Power of Positive Thinking";
                break;
            case 6:
                bookName = "The Analects of Confucius";
                break;
            case 7:
                bookName = "The Alchemist";
                break;
            case 8:
                bookName = "The Moon and Sixpence";
                break;
            case 9:
                bookName = "Gitanjali";
                break;
            case 10:
                bookName = "The Adventures of Huckleberry Finn";
                break;
            default:
                break;
        }
        return bookName;
    }
    public String compareAuthorName(int j){
        String authorName = "";
        switch (j) {
            case 1:
                authorName = "Charles Dickens";
                break;
            case 2:
                authorName = "Jack London";
                break;
            case 3:
                authorName = "Mark Twain";
                break;
            case 4:
                authorName = "Leo Tolstoy";
                break;
            case 5:
                authorName = "Dr. Norman Vincent Peale";
                break;
            case 6:
                authorName = "Confucius";
                break;
            case 7:
                authorName = "Paulo Coelho";
                break;
            case 8:
                authorName = "W.Somerset Maugham";
                break;
            case 9:
                authorName = "Rabindranath Tagore";
                break;
            case 10:
                authorName = "Mark Twain";
                break;
            default:
                break;
        }
        return authorName;

        }


    }
