package com.ucu.BBDD.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="figure_user_offer")
public class FigureUserOffer {


    @EmbeddedId
    @NonNull
    private FigureUserOfferPK figureUserOfferPK;

    @NonNull
    private Integer quantity;



}
