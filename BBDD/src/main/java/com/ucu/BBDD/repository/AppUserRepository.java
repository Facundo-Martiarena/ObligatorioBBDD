package com.ucu.BBDD.repository;

import com.ucu.BBDD.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByEmail(String email);
}
