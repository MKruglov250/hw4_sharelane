package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;
import org.example.model.BookModel;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;

public class LoginPageTest extends BaseTest {

    LoginPage loginPage = new LoginPage();

    @BeforeClass
    public void openMainPage() {
        open("cgi-bin/main.py");
    }

    @Test
    public void checkLoginWithValidCredentialsSucceeds() throws IOException, ParseException {
        Assert.assertTrue(loginPage.checkLoginOrRegisterAndLogin());
    }

    @Test
    public void test() throws IOException {

        var path = Paths.get("utils/books.json");

        String json = new String(Files.readAllBytes(path));

        var gson = new Gson();

        Type listType = new TypeToken<ArrayList<BookModel>>(){}.getType();

        List<BookModel> books = gson.fromJson(json, listType);
        var author = books.get(1).Author;

    }

    // valid
    // non valid usr pass
    // non valid -  pass
    // non valis usr -
    // @Test
    //public void checkLoginWithValidCredentialsSucceeds() {

    //VAR
    //LoginPage loginPage = new LoginPage();

    //var user = UserBuilder.getAdminUser();


    //ACTION
    // var homePage = loginPage.login(user);

    //ASSERT
    // Assert.assertTrue(homePage.validate());




    @AfterClass
    public void doLogout() {
        UtilitiesSharelane.logout();
    }


}