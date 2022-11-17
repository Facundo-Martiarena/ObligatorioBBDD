package com.ucu.BBDD.controller;

import com.ucu.BBDD.entity.Figure;
import com.ucu.BBDD.model.FigureRequestDTO;
import com.ucu.BBDD.model.LoginRequestDTO;
import com.ucu.BBDD.model.OkResponseDTO;
import com.ucu.BBDD.model.UserResponseDTO;
import com.ucu.BBDD.service.FigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FigureController {

    @Autowired
    private FigureService figureService;

        // Asociar una figurita a un usuario
    @PostMapping("/addFigure")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public OkResponseDTO addFigure(@RequestBody FigureRequestDTO figureRequestDTO){
        boolean linkFigureToUser = figureService.linkFigureToUser(figureRequestDTO);
        return new OkResponseDTO(linkFigureToUser);
    }

    @GetMapping("/figures")
    public List<Figure> findAllFigures(){
        return figureService.getFigures();
    }

}
