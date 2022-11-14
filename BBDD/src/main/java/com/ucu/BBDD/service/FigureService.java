package com.ucu.BBDD.service;

import com.ucu.BBDD.entity.Figure;
import com.ucu.BBDD.entity.UserFigure;
import com.ucu.BBDD.entity.UserFigurePK;
import com.ucu.BBDD.model.FigureRequestDTO;
import com.ucu.BBDD.repository.FigureRepository;
import com.ucu.BBDD.repository.UserFigureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FigureService {
    @Autowired
    private FigureRepository figureRepository;
    @Autowired
    private UserFigureRepository userFigureRepository;

    public List<Figure> getFigures(){
        return figureRepository.findAll();
    }

    public Figure getFigure(String number) {
        return figureRepository.findById(number).orElse(null);
    }

    public Figure saveFigure(Figure figure){
        return figureRepository.save(figure);
    }

    public boolean linkFigureToUser(FigureRequestDTO figureRequestDTO) {
        Figure figure = this.getFigure(figureRequestDTO.getFigureNumber());
        if (figure != null){
            UserFigurePK userFigurePK = new UserFigurePK(
                    figureRequestDTO.getFigureState(),
                    figureRequestDTO.getEmail(),
                    figureRequestDTO.getFigureNumber());

            UserFigure userFigure = userFigureRepository.findById(userFigurePK).orElse(null);
            if (userFigure != null ){
                Integer quantity = userFigure.getQuantity() + 1;
                userFigureRepository.save(new UserFigure(userFigurePK, quantity));

            }else{
                userFigureRepository.save(new UserFigure(userFigurePK, 1));
            }

            return true;
        }
        return false;
    }

//    public String deleteFigure(String number){
//        figureRepository.deleteById(number);
//        return "Figure removed";
//    }

//    public Figure updateFigure(Figure figure){
//        Figure existingFigure = figureRepository.findById(figure.getFigurePK()).orElse(null);
//
//        existingFigure.setDescription(figure.getDescription());
//        existingFigure.setImage(figure.getImage());
//
//        return figureRepository.save(existingFigure);
//    }
}
