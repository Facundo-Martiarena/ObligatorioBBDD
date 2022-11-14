package com.ucu.BBDD.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {

    private boolean accepted;
    private String email;

    public UserResponseDTO(boolean accepted, String email) {
        this.accepted = accepted;
        this.email = email;
    }

    public UserResponseDTO() {
    }
}
