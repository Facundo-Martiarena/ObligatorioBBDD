package com.ucu.BBDD.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FigureOfferDTO {
    private String number;
    private String state_damage;
    private Integer quantity;

    public FigureOfferDTO(String number, String state_damage, Integer quantity) {
        this.number = number;
        this.state_damage = state_damage;
        this.quantity = quantity;
    }

    public FigureOfferDTO() {
    }
}
