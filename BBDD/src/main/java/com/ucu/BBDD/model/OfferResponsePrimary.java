package com.ucu.BBDD.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class OfferResponsePrimary {

    private String description_publisher;
    private Integer id_offer;
    private String state_offer;

    private Integer publication_id;

    public OfferResponsePrimary(String description_publisher, Integer id_oferta, String state_offer, Integer publication_id) {
        this.description_publisher = description_publisher;
        this.id_offer = id_oferta;
        this.state_offer = state_offer;
        this.publication_id = publication_id;
    }

    public OfferResponsePrimary() {
    }
}
