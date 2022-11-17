package com.ucu.BBDD.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_figure")
public class UserFigure {

    @EmbeddedId
    @NonNull
    private UserFigurePK userFigurePK;
    @NonNull
    private Integer quantity;

}
