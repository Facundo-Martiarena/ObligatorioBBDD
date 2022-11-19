package com.ucu.BBDD.model;

import com.ucu.BBDD.entity.Figure;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OfferResponseDTO {

    private String description_publication;
    private List<String> description_bidder;
    private String state_offer;

    public OfferResponseDTO(String description_publication, List<String> description_bidder, String state_offer) {
        this.description_publication = description_publication;
        this.description_bidder = description_bidder;
        this.state_offer = state_offer;
    }
}
