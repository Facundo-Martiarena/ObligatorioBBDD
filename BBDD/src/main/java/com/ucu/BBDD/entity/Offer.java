package com.ucu.BBDD.entity;

import com.ucu.BBDD.model.StateFigure;
import com.ucu.BBDD.model.StateOffer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="offer")
public class Offer {

    @Id
    private String idOffer;
    private StateOffer state;
    private Date acepted_date;
    private UserFigurePK userFigurePKBidder;
    private Integer publicationId;




}
