package com.ucu.BBDD.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class OfferResponseList {

    private List<OffersResponse> listOffers;

    public void OffersResponse(List<OffersResponse> listOffers){
        this.listOffers = listOffers;
    }
    public void OffersResponse(){

    }


}
