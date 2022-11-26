package com.ucu.BBDD.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseCreateOfferDTO {
    private String message;

    public ResponseCreateOfferDTO(String message) {
        this.message = message;
    }

    public ResponseCreateOfferDTO() {
    }
}
