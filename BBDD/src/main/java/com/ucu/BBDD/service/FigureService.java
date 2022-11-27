package com.ucu.BBDD.service;

import com.ucu.BBDD.entity.UserFigure;
import com.ucu.BBDD.entity.UserFigurePK;
import com.ucu.BBDD.model.FigureRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class FigureService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean linkFigureToUser(FigureRequestDTO figureRequestDTO) {
        String sql = String.format("INSERT INTO public.user_figure as uf(" +
                " email, number, state_damage, quantity)" +
                " VALUES ('%s', '%s', '%s', 1)" +
                " ON CONFLICT (email, number, state_damage)" +
                " DO" +
                " UPDATE SET quantity = uf.quantity + 1;",figureRequestDTO.getEmail(),figureRequestDTO.getFigureNumber(),figureRequestDTO.getFigureState());

        UserFigure result = jdbcTemplate.queryForObject(sql,((rs, rowNum) -> new UserFigure(
                new UserFigurePK(

                        rs.getString("state_damage"),
                        rs.getString("email"),
                        rs.getString("number")

                ),

                Integer.parseInt(rs.getString("quantity"))

        )));

        if(result != null){
            return true;
        }else{
            return false;
        }


    }
}
