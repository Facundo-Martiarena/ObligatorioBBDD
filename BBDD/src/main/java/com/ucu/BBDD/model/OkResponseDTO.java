package com.ucu.BBDD.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OkResponseDTO {
    private boolean accepted;

    public OkResponseDTO(boolean accepted) {
        this.accepted = accepted;
    }

    public OkResponseDTO() {
    }
}
