package com.ucu.BBDD.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
public class UserFigurePK implements Serializable {

    private static final long serialVersionUID = 1373167097440998364L;
    private String state_damage;
    private String email;
    private String number;

    public UserFigurePK(String state_damage, String email, String number) {
        this.state_damage = state_damage;
        this.email = email;
        this.number = number;
    }

    public UserFigurePK() {
    }
}
