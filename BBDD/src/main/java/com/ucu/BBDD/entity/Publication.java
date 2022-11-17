package com.ucu.BBDD.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="publication")
public class Publication {

    @Id
    @NotNull
    private Integer publication_id;
    @NonNull
    private String pending_exchange;
    @NonNull
    private Date date;
    @NonNull
    private Boolean activated;

    private PublicationUserFigureFK publicationUserFigureFK;

}
