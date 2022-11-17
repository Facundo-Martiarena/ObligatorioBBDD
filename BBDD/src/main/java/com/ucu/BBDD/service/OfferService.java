package com.ucu.BBDD.service;

import com.ucu.BBDD.entity.AppUser;
import com.ucu.BBDD.entity.Offer;
import com.ucu.BBDD.entity.Publication;
import com.ucu.BBDD.model.OfferResponseDTO;
import com.ucu.BBDD.repository.AppUserRepository;
import com.ucu.BBDD.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Offer> getOffers(){
        return offerRepository.findAll();
    }

    /*public List<OfferResponseDTO> getOffers(String email){
       return new ArrayList<>();
    }*/

    public Offer saveOffer(Offer offer){

        return offerRepository.save(offer);
    }

    public String deleteOffer(String idOffer){
        offerRepository.deleteById(idOffer);
        return "Offer removed";
    }

    public List<OfferResponseDTO> getOffersUserMe(String email){

        String sql = String.format("SELECT pub.email, o.number, f1.description as description_bidder, f2.description as description_publisher, o.state" +
                "                FROM public.figure as f1,public.figure as f2, public.offer as o, public.publication as pub" +
                "                WHERE o.email = '%s' AND f1.number = o.number AND f2.number = pub.number_f;",email);
        return jdbcTemplate.query(sql, (rs, rowNum) -> new OfferResponseDTO(
                rs.getString("description_bidder"),
                rs.getString("description_publisher"),
                rs.getString("state")

        ));

    }

//    public Offer updateOffer(Offer offer){
//        Offer existingOffer = offerRepository.findById(offer.getIdOffer()).orElse(null);
//        existingOffer.setAcepted_date(offer.getAcepted_date());
//        existingOffer.setState(offer.getState());
//
//        return offerRepository.save(existingOffer);
//    }
}
