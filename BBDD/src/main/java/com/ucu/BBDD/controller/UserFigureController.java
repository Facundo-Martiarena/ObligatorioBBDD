package com.ucu.BBDD.controller;

import com.ucu.BBDD.entity.UserFigure;
import com.ucu.BBDD.entity.UserFigurePK;
import com.ucu.BBDD.model.UserFiguresResponseDTO;
import com.ucu.BBDD.service.UserFigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserFigureController {

    @Autowired
    private UserFigureService userFigureService;

    @PostMapping("/addUserFigure")
    public UserFigure addUserFigure(@RequestBody UserFigure userFigure){
        return userFigureService.saveUserFigure(userFigure);
    }


    @GetMapping("/userFigures/{email}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public List<UserFiguresResponseDTO> findAllUserFigure(@PathVariable("email") String email){
        return userFigureService.getUserFigures(email);
    }


    @PutMapping("/updateUserFigure")
    public UserFigure updateUserFigure(@RequestBody UserFigure userFigure){
        return userFigureService.updateUserFigure(userFigure);
    }
}
