package com.ucu.BBDD.service;

import com.ucu.BBDD.entity.*;
import com.ucu.BBDD.model.PublicationResponseDTO;
import com.ucu.BBDD.model.PublicationsResponseDTO;
import com.ucu.BBDD.model.UserFiguresResponseDTO;
import com.ucu.BBDD.repository.FigureRepository;
import com.ucu.BBDD.repository.PublicationRepository;
import lombok.Setter;
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


//    public List<Publication> getPublication(){
//        return publicationRepository.findAll();
//    }

    public List<PublicationResponseDTO> getPublications(String email){
        return jdbcTemplate.query(String.format("SELECT p.email, p.number_f, p.state_damage, f.description" +
                        "FROM public.publication as p, public.figure as f" +
                        "WHERE p.activated = TRUE AND p.number_f = f.number" +
                        "AND p.email <> '%s", email),
                ((rs, rowNum) -> new PublicationResponseDTO(
                        rs.getString("state_damage"),
                        rs.getString("email"),
                        rs.getString("number"),
                        rs.getString("description")
                )));

    }

    public Publication savePublication(String id){

        String sql = String.format("INSERT INTO public.publication(email, number_f, state_damage, activated, date, pending_exchange)" +
                "SELECT usr.email, f.number, f.state_damage, false, (SELECT NOW()::timestamp), 'No acepted'" +
                "FROM public.user_figure as f, public.appuser as usr WHERE f.number = '%s'",id);

        return jdbcTemplate.queryForObject(sql,((rs, rowNum) -> new Publication(
                new PublicationPK(
                        rs.getString("email"),
                        rs.getString("number_f"),
                        rs.getString("state_damage")
                ),
                rs.getString("pending_exchange"),
                rs.getDate("date"),
                rs.getBoolean("activated")git p
        )));
    }

    public String deletePublication(PublicationPK publicationPK){
        publicationRepository.deleteById(publicationPK);
        return "Figure removed";
    }

    public Publication updatePublication(Publication publication){
        Publication existingPublication = publicationRepository.findById(publication.getPublicationPK()).orElse(null);
        existingPublication.setPublicationPK(publication.getPublicationPK());
        existingPublication.setDate(publication.getDate());
        existingPublication.setActivated(publication.getActivated());
        existingPublication.setPending_exchange(publication.getPending_exchange());

        return publicationRepository.save(existingPublication);
    }
}
