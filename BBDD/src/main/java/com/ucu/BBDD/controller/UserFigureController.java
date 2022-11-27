package com.ucu.BBDD.controller;

import com.ucu.BBDD.model.UserFigureResponse;
import com.ucu.BBDD.service.UserFigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserFigureController {

    @Autowired
    private UserFigureService userFigureService;

    @GetMapping("/userFigures/{email}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public UserFigureResponse findAllUserFigure(@PathVariable("email") String email){
        return userFigureService.getUserFigures(email);
    }
}
