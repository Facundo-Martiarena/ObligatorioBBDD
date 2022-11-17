package com.ucu.BBDD.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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
    @NonNull
    private String email;
    @NonNull
    private String name;
    @NonNull
    private String lastname;
    @NonNull
    private String ci;
    @NonNull
    private String password;
    private String phone;
}
