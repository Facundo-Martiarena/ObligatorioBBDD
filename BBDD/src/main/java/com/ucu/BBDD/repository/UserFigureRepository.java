package com.ucu.BBDD.repository;

import com.ucu.BBDD.entity.UserFigure;
import com.ucu.BBDD.entity.UserFigurePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFigureRepository extends JpaRepository<UserFigure, UserFigurePK> {
    UserFigure findByUserFigurePK(UserFigurePK userFigurePK);
}
