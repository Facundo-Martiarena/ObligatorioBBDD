package com.ucu.BBDD.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class OfferResponsePrimary {

    private String description_publisher;
    private String description_bidder;
    private String state_offer;

    public OfferResponsePrimary(String description_publisher, String description_bidder, String state_offer) {
        this.description_publisher = description_publisher;
        this.description_bidder = description_bidder;
        this.state_offer = state_offer;
    }
}
