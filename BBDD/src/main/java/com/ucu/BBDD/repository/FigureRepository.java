package com.ucu.BBDD.repository;

import com.ucu.BBDD.entity.Figure;
import com.ucu.BBDD.entity.FigurePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FigureRepository extends JpaRepository<Figure, FigurePK>{
    Figure findByFigurePK(FigurePK figurePK);
}
