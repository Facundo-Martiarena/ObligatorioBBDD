package com.ucu.BBDD.entity;

import com.ucu.BBDD.model.StateFigure;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class PublicationPK implements Serializable {

    private static final long serialVersionUID = 6337440097868762236L;
    private String email;
    private String number_f;
    private StateFigure state_damage;

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

    public StateFigure getState_damage() {
        return state_damage;
    }

    public void setState_damage(StateFigure state_damage) {
        this.state_damage = state_damage;
    }
}
