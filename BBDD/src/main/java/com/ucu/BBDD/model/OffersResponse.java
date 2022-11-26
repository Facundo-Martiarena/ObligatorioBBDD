package com.ucu.BBDD.model;

import com.ucu.BBDD.entity.AppUser;
import com.ucu.BBDD.entity.Offer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OffersResponse {
    private String description_bidder; // descripcion de la figurita a la cual se realiza la oferta
    private List<String> description_publisher;
    private String state_offer;
    private Integer id_offer;
    private Integer publication_id;

    public OffersResponse(String description_bidder, List<String> description_publisher, String state_offer, Integer id_offer, Integer publication_id) {
        this.description_bidder = description_bidder;
        this.description_publisher = description_publisher;
        this.state_offer = state_offer;
        this.id_offer = id_offer;
        this.publication_id = publication_id;
    }

    public OffersResponse() {
    }
}
