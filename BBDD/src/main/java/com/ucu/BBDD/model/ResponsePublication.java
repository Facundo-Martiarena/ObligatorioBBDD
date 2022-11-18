package com.ucu.BBDD.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponsePublication {

    private List<PublicationResponseDTO> publications;

    public ResponsePublication(List<PublicationResponseDTO> publications) {
        this.publications = publications;
    }

    public ResponsePublication() {
    }
}
