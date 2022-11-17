package com.ucu.BBDD.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestBodyUser {

    private String email;
    private String password;

    public RequestBodyUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public RequestBodyUser() {
    }
}
