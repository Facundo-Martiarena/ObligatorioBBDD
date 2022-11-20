package com.ucu.BBDD.service;

import com.ucu.BBDD.entity.*;
import com.ucu.BBDD.model.PublicationResponseDTO;
import com.ucu.BBDD.model.RequestAddPublicationDTO;
import com.ucu.BBDD.model.ResponsePublication;
import com.ucu.BBDD.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


        List<PublicationResponseDTO> listPublications = jdbcTemplate.query(String.format("SELECT p.email, p.number_f, p.activated, f.description, p.publication_id" +
                        " FROM public.publication as p, public.figure as f" +
                        " WHERE p.activated = TRUE AND p.number_f = f.number" +
                        " AND p.email <> '%s';", email),
                ((rs, rowNum) -> new PublicationResponseDTO(
                        rs.getString("activated"),
                        rs.getString("email"),
                        rs.getString("number_f"),
                        rs.getString("description"),
                        rs.getInt("publication_id")
                )));

        ResponsePublication result = new ResponsePublication(listPublications);

        return result;

    }

    public ResponsePublication getPublicationsUserMe(String email){
        List<PublicationResponseDTO> listPublications = jdbcTemplate.query(String.format("SELECT p.email, p.number_f, p.activated, f.description, p.publication_id" +
                        " FROM public.publication as p, public.figure as f" +
                        " WHERE  p.number_f = f.number" +
                        " AND p.email = '%s'", email),
                ((rs, rowNum) -> new PublicationResponseDTO(
                        rs.getString("activated"),
                        rs.getString("email"),
                        rs.getString("number_f"),
                        rs.getString("description"),
                        rs.getInt("publication_id")
                )));

        ResponsePublication result = new ResponsePublication(listPublications);

        return result;

    }

    public Publication savePublication(RequestAddPublicationDTO requestAddPublicationDTO){


        String sql = String.format("INSERT INTO public.publication(activated, date, pending_exchange, email, number_f, state_damage)" +
                " SELECT false, (SELECT NOW()::timestamp), 'No acepted', usr.email, f.number, f.state_damage" +
                " FROM public.user_figure as f, public.appuser as usr WHERE f.number = '%s' AND usr.email='%s'",requestAddPublicationDTO.getPublication_id(),requestAddPublicationDTO.getEmail());

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

        String queryPubId = String.format("SELECT pub.publication_id " +
                " FROM public.publication as pub, public.publication as pub2" +
                " WHERE pub.publication_id = '%s' AND pub2.activated = true", publication_id);

        List<Integer> activatedPub = jdbcTemplate.query(queryPubId, (rs, rowNum) -> rs.getInt("publication_id"));

        if(activatedPub.size() < 3 || !activate ){
            String sql = String.format("UPDATE public.publication" +
                    " SET activated= %s " +
                    " WHERE publication_id='%s'",activate,publication_id);
            return jdbcTemplate.update(sql) != 0;
        }

        return false;

    }



    public String deletePublication(PublicationUserFigureFK publicationUserFigureFK){
        publicationRepository.deleteById(publicationUserFigureFK);
        return "Figure removed";
    }



}
