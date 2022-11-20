package com.ucu.BBDD.service;

import com.ucu.BBDD.entity.AppUser;
import com.ucu.BBDD.entity.Offer;
import com.ucu.BBDD.entity.Publication;
import com.ucu.BBDD.model.*;
import com.ucu.BBDD.repository.AppUserRepository;
import com.ucu.BBDD.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.beans.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                " WHERE o.email_bidder = '%s' AND fuo.id_offer = o.id_offer" +
                " AND f1.number = fuo.number_f_offer_bidder AND o.publication_id = pub.publication_id" +
                " AND pub.number_f = f2.number;",email);
        List<OfferResponsePrimary> offerBidderList = jdbcTemplate.query(sql, (rs, rowNum) -> new OfferResponsePrimary(
                rs.getString("description_bidder"),
                rs.getString("description_publisher"),
                rs.getString("state")

        ));

        List<String> descriptions = new ArrayList<>();

        offerBidderList.stream().forEach(offerB -> descriptions.add(offerB.getDescription_publisher()));

        if(descriptions.size() != 0){
            return new OffersResponse(offerBidderList.get(0).getDescription_bidder(),new DescriptionPublisherDTO(descriptions),
                    offerBidderList.get(0).getState_offer());
        }

        return null;
    }

//    public OffersResponse getOffersPublisher(String email){
//
//        String sql = String.format("SELECT pub.email as email_publisher, o.email_bidder as email_bidder, fuo.number_f_offer_bidder, f1.description as description_bidder, f2.description as description_publisher, o.state" +
//                " FROM public.figure as f1,public.figure as f2, public.offer as o, public.publication as pub," +
//                " public.figure_user_offer as fuo" +
//                " WHERE pub.email = '%s' AND fuo.id_offer = o.id_offer" +
//                " AND f1.number = fuo.number_f_offer_bidder AND o.publication_id = pub.publication_id" +
//                " AND pub.number_f = f2.number;",email);
//        List<OfferResponseDTO> offerList = jdbcTemplate.query(sql, (rs, rowNum) -> new OfferResponseDTO(
//                rs.getString("description_bidder"),
//                rs.getString("description_publisher"),
//                rs.getString("state")
//        ));
//
//        return new OffersResponse(offerList);
//    }

    public boolean saveOffer(CreateOfferRequestDTO createOfferRequestDTO) {
        Integer id_offer;
        String sql = String.format("INSERT INTO public.offer(" +
                        " email_bidder, publication_id, state)" +
                        " VALUES ('%s', %d, '%s')" +
                        " RETURNING id_offer;"
                ,createOfferRequestDTO.getEmail_bidder()
                ,createOfferRequestDTO.getId_publication()
                ,createOfferRequestDTO.getState_offer());
        System.out.println(sql);
        id_offer = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> rs.getInt("id_offer"));

        List<FigureOfferDTO> figures = createOfferRequestDTO.getFigures();

        figures.forEach(
                f -> jdbcTemplate.update(String.format("INSERT INTO public.figure_user_offer(" +
                " id_offer, number_f_offer_bidder, state_damage_f_offer_bidder, quantity)" +
                " VALUES ('%d','%s' , '%s', '%d');", id_offer, f.getNumber(), f.getState_damage(), f.getQuantity())));
    return true;
    }


//    public Offer updateOffer(Offer offer){
//        Offer existingOffer = offerRepository.findById(offer.getIdOffer()).orElse(null);
//        existingOffer.setAcepted_date(offer.getAcepted_date());
//        existingOffer.setState(offer.getState());
//
//        return offerRepository.save(existingOffer);
//    }
}
