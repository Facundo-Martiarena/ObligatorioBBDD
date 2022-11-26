package com.ucu.BBDD.service;

import com.ucu.BBDD.entity.Figure;
import com.ucu.BBDD.entity.UserFigure;
import com.ucu.BBDD.entity.UserFigurePK;
import com.ucu.BBDD.model.FigureRequestDTO;
import com.ucu.BBDD.model.FiguresResponse;
import com.ucu.BBDD.repository.FigureRepository;
import com.ucu.BBDD.repository.UserFigureRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;

@Service
public class FigureService {
    @Autowired
    private FigureRepository figureRepository;
    @Autowired
    private UserFigureRepository userFigureRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;


//    public FiguresResponse getFigures(){
//        List<Figure> figureList = figureRepository.findAll();
//        return new FiguresResponse(figureList);
//    }

//    public Figure getFigure(String number) {
//        return figureRepository.findById(number).orElse(null);
//    }
//
//    public Figure saveFigure(Figure figure){
//        return figureRepository.save(figure);
//    }

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
