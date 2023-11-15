package org.example.model;

import com.github.javafaker.Faker;
import org.example.utilities.LoginUtils;
import org.example.utilities.PropertyReader;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class UserModelBuilder {

    public static UserModel getSimpleUser() throws IOException, ParseException {
        return new UserModel.UserModelBuilder()
                .email(LoginUtils.getLogin())
                .password(LoginUtils.getPassword())
                .build();
    }


    public static UserModel getAdminUser() throws IOException, ParseException {
        return new UserModel.UserModelBuilder()
                .firstName(PropertyReader.getAdminFirstNameProperty())
                .lastName(PropertyReader.getAdminLastNameProperty())
                .email(LoginUtils.getLogin())
                .password(PropertyReader.getAdminPasswordProperty())
                .zipCode(PropertyReader.getAdminZipcodeProperty()).build();
    }

    public static UserModel getIncorrectUser(){
        Faker faker = new Faker();

        return new UserModel.UserModelBuilder()
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .build();
    }
}
