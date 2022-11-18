package com.ucu.BBDD.model;

import com.ucu.BBDD.entity.Figure;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class FiguresResponse {

    private List<Figure> figures;

    public FiguresResponse(List<Figure> figures) {
        this.figures = figures;
    }

    public FiguresResponse() {
    }
}
