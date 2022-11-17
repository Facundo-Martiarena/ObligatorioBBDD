package com.ucu.BBDD.entity;

import com.ucu.BBDD.model.StateFigure;
import com.ucu.BBDD.model.StateOffer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="offer")
public class Offer {

    @Id
    @NonNull
    private Integer idOffer;
    @NonNull
    private String state;
    @NonNull
    private Date acepted_date;
    @NonNull
    private String email_bidder;
    @NonNull
    private Integer publication_id;




}
