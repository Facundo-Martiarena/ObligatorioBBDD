package com.ucu.BBDD.entity;

import lombok.NonNull;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FigureUserOfferPK implements Serializable {

    private static final long serialVersionUID = -7532564017503542158L;

    @NonNull
    private Integer idOffer;
    @NonNull
    private String number_f_offer_bidder;
    @NonNull
    private String state_damage_f_offer_bidder;


}
