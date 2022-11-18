package com.ucu.BBDD.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestBodyUF {

    private String state_damage;
    private String email;
    private String number;

    public RequestBodyUF() {
    }

    public RequestBodyUF(String state_damage, String email, String number) {
        this.state_damage = state_damage;
        this.email = email;
        this.number = number;
    }
}
