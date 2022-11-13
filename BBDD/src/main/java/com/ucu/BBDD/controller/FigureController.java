package com.ucu.BBDD.controller;

import com.ucu.BBDD.entity.AppUser;
import com.ucu.BBDD.entity.Figure;
import com.ucu.BBDD.entity.FigurePK;
import com.ucu.BBDD.service.FigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FigureController {

    @Autowired
    private FigureService figureService;

    @PostMapping("/addFigure")
    public Figure addFigure(@RequestBody Figure figure){
        return figureService.saveFigure(figure);
    }


    @GetMapping("/figures")
    public List<Figure> findAllFigures(){
        return figureService.getFigures();
    }

    @DeleteMapping("/deleteFigure/{figurePK}")
    public String deleteFigure(@PathVariable FigurePK figurePK){
        return figureService.deleteFigure(figurePK);
    }

    @PutMapping("/updateFigure")
    public Figure updateFigure(@RequestBody Figure figure){
        return figureService.updateFigure(figure);
    }
}
