package com.ucu.BBDD.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="figure")
public class Figure {

    @EmbeddedId
    private FigurePK figurePK;
    private String image;
    private String description;
}
