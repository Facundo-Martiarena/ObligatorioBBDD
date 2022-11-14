package com.ucu.BBDD.model;


import com.ucu.BBDD.entity.PublicationPK;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicationResponseDTO {
    private String description;
    private StateFigure stateFigure;
    private String nameUser;
    private PublicationPK id;

    public PublicationResponseDTO(String description, StateFigure stateFigure, String nameUser, PublicationPK id) {
        this.description = description;
        this.stateFigure = stateFigure;
        this.nameUser = nameUser;
        this.id = id;
    }

    public PublicationResponseDTO() {
    }
}
