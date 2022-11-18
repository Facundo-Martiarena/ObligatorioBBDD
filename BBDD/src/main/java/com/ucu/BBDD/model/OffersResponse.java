package com.ucu.BBDD.model;

import com.ucu.BBDD.entity.AppUser;
import com.ucu.BBDD.entity.Offer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class OffersResponse {

    private List<OfferResponseDTO> offers;

    public OffersResponse() {
    }

    public OffersResponse(List<OfferResponseDTO> offers) {
        this.offers = offers;
    }
}
