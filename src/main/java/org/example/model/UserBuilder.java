package org.example.model;

import com.github.javafaker.Faker;
import org.example.PropertyReader;
import org.example.model.UserModel;


public class UserBuilder {

    public static UserModel getAdminUser(){
        return new UserModel
                .UserModelBuilder()
                .Name(PropertyReader.getAdminNameProperty())
                .Password(PropertyReader.getAdminPasswordProperty())
                .build();
    }


    public static UserModel getIncorectCredetnials(){

        Faker faker = new Faker();

        return new UserModel
                .UserModelBuilder()
                .Name(faker.finance().iban())
                .Password(faker.internet().password())
                .build();
    }
}
