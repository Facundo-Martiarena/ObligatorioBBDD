package com.ucu.BBDD.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="offer")
public class Offer {

    @Id
    private String id_offer;
    private String state;
    private Date acepted_date;
    private String email;
    private String password;
    private String phone;
}
