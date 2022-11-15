package com.ucu.BBDD.model;

import com.ucu.BBDD.entity.Figure;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OfferResponseDTO {

    private Figure figureBidder;
    private List<Figure> figuresPublisher;
    private StateOffer stateOffer;

    public OfferResponseDTO(Figure figureBidder, List<Figure> figuresPublisher, StateOffer stateOffer) {
        this.figureBidder = figureBidder;
        this.figuresPublisher = figuresPublisher;
        this.stateOffer = stateOffer;
    }
}
