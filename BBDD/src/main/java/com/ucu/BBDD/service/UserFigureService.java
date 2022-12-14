package com.ucu.BBDD.service;


import com.ucu.BBDD.model.UserFigureResponse;
import com.ucu.BBDD.model.UserFiguresResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserFigureService {

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
}
