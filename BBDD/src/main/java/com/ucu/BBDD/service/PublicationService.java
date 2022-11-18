package com.ucu.BBDD.service;

import com.ucu.BBDD.entity.*;
import com.ucu.BBDD.model.PublicationResponseDTO;
import com.ucu.BBDD.model.ResponsePublication;
import com.ucu.BBDD.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FigureService figureService;


    public ResponsePublication getPublications(String email){


        List<PublicationResponseDTO> listPublications = jdbcTemplate.query(String.format("SELECT p.email, p.number_f, p.state_damage, f.description" +
                        " FROM public.publication as p, public.figure as f" +
                        " WHERE p.activated = TRUE AND p.number_f = f.number" +
                        " AND p.email <> '%s';", email),
                ((rs, rowNum) -> new PublicationResponseDTO(
                        rs.getString("state_damage"),
                        rs.getString("email"),
                        rs.getString("number"),
                        rs.getString("description")
                )));

        ResponsePublication result = new ResponsePublication(listPublications);

        return result;

    }

    public ResponsePublication getPublicationsUserMe(String email){
        List<PublicationResponseDTO> listPublications = jdbcTemplate.query(String.format("SELECT p.email, p.number_f, p.state_damage, f.description" +
                        "FROM public.publication as p, public.figure as f" +
                        "WHERE p.activated = TRUE AND p.number_f = f.number" +
                        "AND p.email = '%s", email),
                ((rs, rowNum) -> new PublicationResponseDTO(
                        rs.getString("state_damage"),
                        rs.getString("email"),
                        rs.getString("number"),
                        rs.getString("description")
                )));

        ResponsePublication result = new ResponsePublication(listPublications);

        return result;

    }

    public Publication savePublication(String id){


        String sql = String.format("INSERT INTO public.publication(activated, date, pending_exchange, email, number_f, state_damage)" +
                "SELECT false, (SELECT NOW()::timestamp), 'No acepted', usr.email, f.number, f.state_damage" +
                "FROM public.user_figure as f, public.appuser as usr WHERE f.number = '%s'",id);

        return jdbcTemplate.queryForObject(sql,((rs, rowNum) -> new Publication(

                rs.getInt("publication_id"),
                rs.getString("pending_exchange"),
                rs.getDate("date"),
                rs.getBoolean("activated"),
                new PublicationUserFigureFK(
                        rs.getString("email"),
                        rs.getString("number_f"),
                        rs.getString("state_damage")
                )
        )));
    }

    public Boolean updateActivatedPublication(Integer publication_id, Boolean activate){
        String sql = String.format("UPDATE public.publication " +
                "SET publication_id=%s, activated=%s;",publication_id,activate );

        return jdbcTemplate.update(sql) != 0;
    }



    public String deletePublication(PublicationUserFigureFK publicationUserFigureFK){
        publicationRepository.deleteById(publicationUserFigureFK);
        return "Figure removed";
    }



}
