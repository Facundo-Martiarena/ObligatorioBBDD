package com.ucu.BBDD.service;


import com.ucu.BBDD.entity.*;
import com.ucu.BBDD.repository.UserFigureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFigureService {

    @Autowired
    private UserFigureRepository userFigureRepository;

    public List<UserFigure> getUserFigure(){
        return userFigureRepository.findAll();
    }

    public UserFigure saveUserFigure(UserFigure userFigure){
        return userFigureRepository.save(userFigure);
    }

    public String deleteUserFigure(UserFigurePK userFigurePK){
        userFigureRepository.deleteById(userFigurePK);
        return "User-Figure removed";
    }

    public UserFigure updateUserFigure(UserFigure userFigure){
        UserFigure existingUserFigure = userFigureRepository.findByUserFigurePK(userFigure.getUserFigurePK());
        existingUserFigure.setUserFigurePK(userFigure.getUserFigurePK());
        existingUserFigure.setQuantity(userFigure.getQuantity());

        return userFigureRepository.save(existingUserFigure);
    }

}
