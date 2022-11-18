package com.ucu.BBDD.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicationResponseDTO {
    private String activated;
    private String email;
    private String number_f;
    private String description;

    private Integer publication_id;

    public PublicationResponseDTO(String activated, String email, String number_f, String description, Integer publication_id) {
        this.activated = activated;
        this.email = email;
        this.number_f = number_f;
        this.description = description;
        this.publication_id = publication_id;
    }

    public PublicationResponseDTO() {
    }
}
