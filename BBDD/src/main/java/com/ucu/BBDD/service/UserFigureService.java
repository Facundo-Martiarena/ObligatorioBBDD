package com.ucu.BBDD.service;


import com.ucu.BBDD.entity.*;
import com.ucu.BBDD.repository.UserFigureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserFigureService {

    @Autowired
    private UserFigureRepository userFigureRepository;

    public List<UserFigure> getUserFigure(String email){
        List<UserFigure> allFigures = userFigureRepository.findAll();
        List<UserFigure> userFigures;
        userFigures =allFigures.stream().filter(fig-> email.equals(fig.getUserFigurePK().getEmail())).collect(Collectors.toList());
        return userFigures;
    }

    public UserFigure saveUserFigure(UserFigure userFigure){
        return userFigureRepository.save(userFigure);
    }

    public String deleteUserFigure(UserFigurePK userFigurePK){
        userFigureRepository.deleteById(userFigurePK);
        return "User-Figure removed";
    }

    public UserFigure updateUserFigure(UserFigure userFigure){
        UserFigure existingUserFigure = userFigureRepository.findById(userFigure.getUserFigurePK()).orElse(null);
        existingUserFigure.setUserFigurePK(userFigure.getUserFigurePK());
        existingUserFigure.setQuantity(userFigure.getQuantity());

        return userFigureRepository.save(existingUserFigure);
    }

}
