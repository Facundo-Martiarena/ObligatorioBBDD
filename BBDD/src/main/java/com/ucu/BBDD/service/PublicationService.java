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
                        "AND p.email <>  '%s", email),
                ((rs, rowNum) -> new PublicationResponseDTO(
                        rs.getString("state_damage"),
                        rs.getString("email"),
                        rs.getString("number"),
                        rs.getString("description")
                )));



//        List<Publication> listPublication = this.getPublication();
//        PublicationsResponseDTO publicationsResponseDTO = new PublicationsResponseDTO();
//        List<PublicationResponseDTO> listPublicationResponseDTOS = null;
//        for(Publication publication : listPublication){
//            if(publication.getPublicationPK().getEmail() != email){
//                listPublicationResponseDTOS.add(new PublicationResponseDTO(figureService.getFigure(publication.getPublicationPK().getNumber_f()).getDescription(),publication.getPublicationPK().getState_damage(),publication.getPublicationPK().getEmail(),publication.getPublicationPK()));
//            }
//        }
//        publicationsResponseDTO.setPublications(listPublicationResponseDTOS);
//
//        return publicationsResponseDTO;
    }

    public Publication savePublication(Publication publication){
        return publicationRepository.save(publication);
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
