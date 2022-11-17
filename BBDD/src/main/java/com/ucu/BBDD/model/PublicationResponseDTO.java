package com.ucu.BBDD.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicationResponseDTO {
    private String state_damage;
    private String email;
    private String number;
    private String description;

    public PublicationResponseDTO(String state_damage, String email, String number, String description) {
        this.state_damage = state_damage;
        this.email = email;
        this.number = number;
        this.description = description;
    }

    public PublicationResponseDTO() {
    }
}
