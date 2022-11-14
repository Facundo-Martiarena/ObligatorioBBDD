package com.ucu.BBDD.repository;

import com.ucu.BBDD.entity.Figure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FigureRepository extends JpaRepository<Figure, String>{
    //Figure findByFigurePK(String number);
}
