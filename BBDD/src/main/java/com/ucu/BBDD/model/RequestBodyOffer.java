package com.ucu.BBDD.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestBodyOffer {


    private String email;
    private Integer publication_id;

    public RequestBodyOffer(String email, Integer publication_id) {
        this.email = email;
        this.publication_id = publication_id;
    }

    public RequestBodyOffer() {
    }
}
