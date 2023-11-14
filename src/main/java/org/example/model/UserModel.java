package org.example.model;

import lombok.*;

@Data
@Builder
public class UserModel {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String zipCode;

}