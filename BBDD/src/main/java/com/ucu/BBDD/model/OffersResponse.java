package com.ucu.BBDD.model;

import com.ucu.BBDD.entity.AppUser;
import com.ucu.BBDD.entity.Offer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class OffersResponse {

    private String description_bidder;
    private DescriptionPublisherDTO description_publisher;
    private String state_offer;

    public OffersResponse(String description_bidder,DescriptionPublisherDTO description_publisher, String state_offer) {
        this.description_bidder = description_bidder;
        this.description_publisher = description_publisher;
        this.state_offer = state_offer;
    }

    public OffersResponse() {
    }
}
