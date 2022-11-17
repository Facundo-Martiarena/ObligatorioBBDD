package com.ucu.BBDD.entity;

import lombok.NonNull;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PublicationUserFigureFK implements Serializable {

    private static final long serialVersionUID = 6337440097868762236L;
    @NonNull
    private String email;
    @NonNull
    private String number_f;
    @NonNull
    private String state_damage;

    public PublicationUserFigureFK(String email, String number_f, String state_damage) {
        this.email = email;
        this.number_f = number_f;
        this.state_damage = state_damage;
    }

    public PublicationUserFigureFK() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber_f() {
        return number_f;
    }

    public void setNumber_f(String number_f) {
        this.number_f = number_f;
    }

    public String getState_damage() {
        return state_damage;
    }

    public void setState_damage(String state_damage) {
        this.state_damage = state_damage;
    }
}
