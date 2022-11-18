package com.ucu.BBDD.model;

import com.ucu.BBDD.entity.AppUser;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UsersResponse {

    private List<AppUser> users;

    public UsersResponse() {
    }

    public UsersResponse(List<AppUser> users) {
        this.users = users;
    }
}
