package com.ucu.BBDD.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestAddPublicationDTO {

    private String email;
    private Integer publication_id;

    public RequestAddPublicationDTO(String email, Integer publication_id) {
        this.email = email;
        this.publication_id = publication_id;
    }

    public RequestAddPublicationDTO() {
    }
}
