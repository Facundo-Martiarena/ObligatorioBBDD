package com.ucu.BBDD.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFiguresResponseDTO {
    private String state_damage;
    private String email;
    private String number;
    private Integer quantity;
    private String description;

    public UserFiguresResponseDTO(String state_damage, String email, String number, Integer quantity, String description) {
        this.state_damage = state_damage;
        this.email = email;
        this.number = number;
        this.quantity = quantity;
        this.description = description;
    }

    public UserFiguresResponseDTO() {
    }
}
