package com.ucu.BBDD.model;

import com.ucu.BBDD.entity.Figure;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UserFigureResponse {

    private List<UserFiguresResponseDTO> userfigures;

    public UserFigureResponse(List<UserFiguresResponseDTO> userfigures) {
        this.userfigures = userfigures;
    }

    public UserFigureResponse() {
    }
}
