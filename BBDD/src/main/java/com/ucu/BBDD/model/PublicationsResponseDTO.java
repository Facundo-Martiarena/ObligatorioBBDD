package com.ucu.BBDD.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PublicationsResponseDTO {

    private List<PublicationResponseDTO> publications;

    public PublicationsResponseDTO(List<PublicationResponseDTO> publications) {
        this.publications = publications;
    }

    public PublicationsResponseDTO() {
    }
}


