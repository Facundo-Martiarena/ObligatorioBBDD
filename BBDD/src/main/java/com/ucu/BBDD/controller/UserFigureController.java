package com.ucu.BBDD.controller;

import com.ucu.BBDD.entity.Publication;
import com.ucu.BBDD.entity.PublicationPK;
import com.ucu.BBDD.entity.UserFigure;
import com.ucu.BBDD.entity.UserFigurePK;
import com.ucu.BBDD.service.PublicationService;
import com.ucu.BBDD.service.UserFigureService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping("/userFigures")
    public List<UserFigure> findAllUserFigure(){
        return userFigureService.getUserFigure();
    }

    @DeleteMapping("/deleteUserFigure/{userFigurePK}")
    public String deleteUserFigure(@PathVariable UserFigurePK userFigurePK){
        return userFigureService.deleteUserFigure(userFigurePK);
    }

    @PutMapping
    public UserFigure updateUserFigure(@RequestBody UserFigure userFigure){
        return userFigureService.updateUserFigure(userFigure);
    }
}
