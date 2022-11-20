package com.ucu.BBDD.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class OfferResponsePrimary {

    private String description_publisher;
    private Integer id_oferta;
    private String state_offer;

    public OfferResponsePrimary(String description_publisher, Integer id_oferta, String state_offer) {
        this.description_publisher = description_publisher;
        this.id_oferta = id_oferta;
        this.state_offer = state_offer;
    }

    public OfferResponsePrimary() {
    }
}
