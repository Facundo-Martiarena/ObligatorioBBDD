package com.ucu.BBDD.service;

import com.ucu.BBDD.entity.AppUser;
import com.ucu.BBDD.entity.Offer;
import com.ucu.BBDD.entity.Publication;
import com.ucu.BBDD.model.OfferResponseDTO;
import com.ucu.BBDD.model.OffersResponse;
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

    public Offer saveOffer(String email, Integer publication_id){

        String sql = String.format("INSERT INTO public.offer(" +
                " acepted_date, email_bidder, publication_id, state)" +
                " SELECT (SELECT NOW()::timestamp), '%s', %s, pub.state_damage" +
                " FROM public.publication as pub" +
                " WHERE pub.publication_id = %s;",email,publication_id, publication_id);

        return jdbcTemplate.queryForObject(sql,(rs, rowNum) -> new Offer(

                rs.getInt("id_offer"),
                rs.getString("state"),
                rs.getDate("acepted_date"),
                rs.getString("email_bidder"),
                rs.getInt("publication_id")
        ));
    }

    public String deleteOffer(String idOffer){
        offerRepository.deleteById(idOffer);
        return "Offer removed";
    }

    public OffersResponse getOffersBidder(String email){

        String sql = String.format("SELECT pub.email as email_publisher, o.email_bidder as email_bidder, fuo.number_f_offer_bidder, f1.description as description_bidder, f2.description as description_publisher, o.state" +
                " FROM public.figure as f1,public.figure as f2, public.offer as o, public.publication as pub," +
                " public.figure_user_offer as fuo" +
                " WHERE o.email = '%s' AND fuo.id_offer = o.id_offer" +
                " AND f1.number = fuo.number_f_offer_bidder AND o.publication_id = pub.publication_id" +
                " AND pub.number_f = f2.number;",email);
        return new OffersResponse(jdbcTemplate.query(sql, (rs, rowNum) -> new OfferResponseDTO(
                rs.getString("description_bidder"),
                rs.getString("description_publisher"),
                rs.getString("state")

        )));

    }

    public OffersResponse getOffersPublisher(String email){

        String sql = String.format("SELECT pub.email as email_publisher, o.email_bidder as email_bidder, fuo.number_f_offer_bidder, f1.description as description_bidder, f2.description as description_publisher, o.state" +
                " FROM public.figure as f1,public.figure as f2, public.offer as o, public.publication as pub," +
                " public.figure_user_offer as fuo" +
                " WHERE pub.email = '%s' AND fuo.id_offer = o.id_offer" +
                " AND f1.number = fuo.number_f_offer_bidder AND o.publication_id = pub.publication_id" +
                " AND pub.number_f = f2.number;",email);
        List<OfferResponseDTO> offerList = jdbcTemplate.query(sql, (rs, rowNum) -> new OfferResponseDTO(
                rs.getString("description_bidder"),
                rs.getString("description_publisher"),
                rs.getString("state")
        ));

        return new OffersResponse(offerList);
    }



//    public Offer updateOffer(Offer offer){
//        Offer existingOffer = offerRepository.findById(offer.getIdOffer()).orElse(null);
//        existingOffer.setAcepted_date(offer.getAcepted_date());
//        existingOffer.setState(offer.getState());
//
//        return offerRepository.save(existingOffer);
//    }
}
