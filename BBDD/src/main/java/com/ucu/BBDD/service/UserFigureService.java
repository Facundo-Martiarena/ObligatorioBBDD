package com.ucu.BBDD.service;


import com.ucu.BBDD.entity.*;
import com.ucu.BBDD.model.OkResponseDTO;
import com.ucu.BBDD.model.UserFigureResponse;
import com.ucu.BBDD.model.UserFiguresResponseDTO;
import com.ucu.BBDD.repository.UserFigureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserFigureService {

    @Autowired
    private UserFigureRepository userFigureRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserFigureResponse getUserFigures(String email){
        return new UserFigureResponse(jdbcTemplate.query(String.format("SELECT uf.*, f.description FROM public.user_figure as uf, public.figure as f WHERE email = '%s' AND f.number = uf.number", email),
                ((rs, rowNum) -> new UserFiguresResponseDTO(
                        rs.getString("state_damage"),
                        rs.getString("email"),
                        rs.getString("number"),
                        rs.getInt("quantity"),
                        rs.getString("description")
                ))));
    }

//    public UserFigure saveUserFigure(UserFigure userFigure){
//        return userFigureRepository.save(userFigure);
//    }


//    public UserFigure updateUserFigure(UserFigure userFigure){
//        UserFigure existingUserFigure = userFigureRepository.findById(userFigure.getUserFigurePK()).orElse(null);
//        existingUserFigure.setUserFigurePK(userFigure.getUserFigurePK());
//        existingUserFigure.setQuantity(userFigure.getQuantity());
//
//        return userFigureRepository.save(existingUserFigure);
//    }

}
