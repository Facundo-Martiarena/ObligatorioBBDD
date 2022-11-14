package com.ucu.BBDD.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FigureRequestDTO {
    private String email;
    private String figureNumber;
    private String figureState;

    public FigureRequestDTO(String email, String figureNumber, String figureState) {
        this.email = email;
        this.figureNumber = figureNumber;
        this.figureState = figureState;
    }

    public FigureRequestDTO() {
    }
}
