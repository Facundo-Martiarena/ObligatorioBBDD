package com.ucu.BBDD.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="figure")
public class Figure {

    @Id
    @NonNull
    private String number;
    private String image;
    @NonNull
    private String description;
}
