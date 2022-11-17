package com.ucu.BBDD.model;

import com.ucu.BBDD.entity.Figure;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OfferResponseDTO {

    private String description_publication;
    private String description_offer;
    private String state_offer;

    public OfferResponseDTO(String description_publication, String description_offer, String state_offer) {
        this.description_publication = description_publication;
        this.description_offer = description_offer;
        this.state_offer = state_offer;
    }
}
