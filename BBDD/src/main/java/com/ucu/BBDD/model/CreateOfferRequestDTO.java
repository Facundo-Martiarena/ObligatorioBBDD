package com.ucu.BBDD.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


public class CreateOfferRequestDTO {
    private Integer id_publication;
    private String email_bidder;
    private String state_offer;
    private List<FigureOfferDTO> figures;

    public CreateOfferRequestDTO(Integer id_publication, String email_bidder, String state_offer, List<FigureOfferDTO> figures) {
        this.id_publication = id_publication;
        this.email_bidder = email_bidder;
        this.state_offer = state_offer;
        this.figures = figures;
    }

    public Integer getId_publication() {
        return id_publication;
    }

    public void setId_publication(Integer id_publication) {
        this.id_publication = id_publication;
    }

    public String getEmail_bidder() {
        return email_bidder;
    }

    public void setEmail_bidder(String email_bidder) {
        this.email_bidder = email_bidder;
    }

    public String getState_offer() {
        if ("".equals(this.state_offer)){
            return "ACTIVO";
        }
        return this.state_offer;
    }

    public void setState_offer(String state_offer) {
        this.state_offer = state_offer;
    }

    public List<FigureOfferDTO> getFigures() {
        return figures;
    }

    public void setFigures(List<FigureOfferDTO> figures) {
        this.figures = figures;
    }

    public CreateOfferRequestDTO() {
    }
}
