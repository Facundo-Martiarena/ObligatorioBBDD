package com.ucu.BBDD.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FigureUserOfferPK implements Serializable {

    private static final long serialVersionUID = -7532564017503542158L;
    private String id_offer;
    private Integer number_f_offer_bidder;
    private String countryf_offer_bidder;
    private String state_damage_f_offer_bidder;
    private String email_bidder;

}
