package com.ucu.BBDD.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
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

    @EmbeddedId
    private PublicationPK publicationPK;
    private String pending_exchange;
    private Date date;
    private Boolean activated;

}
