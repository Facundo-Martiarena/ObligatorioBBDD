package com.ucu.BBDD.service;

import com.ucu.BBDD.entity.Figure;
import com.ucu.BBDD.entity.FigurePK;
import com.ucu.BBDD.repository.FigureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FigureService {
    @Autowired
    private FigureRepository figureRepository;

    public List<Figure> getFigures(){
        return figureRepository.findAll();
    }

    public Figure saveFigure(Figure figure){
        return figureRepository.save(figure);
    }

    public String deleteFigure(FigurePK figurePK){
        figureRepository.deleteById(figurePK);
        return "Figure removed";
    }

    public Figure updateFigure(Figure figure){
        Figure existingFigure = figureRepository.findByFigurePK(figure.getFigurePK());

        existingFigure.setDescription(figure.getDescription());
        existingFigure.setImage(figure.getImage());

        return figureRepository.save(existingFigure);
    }
}
