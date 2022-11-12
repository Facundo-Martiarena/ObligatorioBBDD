package com.ucu.BBDD.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="appuser")
public class AppUser {
    @Id
    private String email;
    private String name;
    private String lastname;
    private String ci;
    private String password;
    private String phone;
}
