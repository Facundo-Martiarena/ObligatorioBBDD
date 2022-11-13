package com.ucu.BBDD.service;

import com.ucu.BBDD.entity.AppUser;
import com.ucu.BBDD.entity.Offer;
import com.ucu.BBDD.entity.Publication;
import com.ucu.BBDD.repository.AppUserRepository;
import com.ucu.BBDD.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    public List<Offer> getOffers(){
        return offerRepository.findAll();
    }

    public Offer saveOffer(Offer offer){
        return offerRepository.save(offer);
    }

    public String deleteOffer(String id_offer){
        offerRepository.deleteById(id_offer);
        return "Offer removed";
    }

    public Offer updateOffer(Offer offer){
        Offer existingOffer = offerRepository.findById(offer.getId_offer()).orElse(null);
        existingOffer.setAcepted_date(offer.getAcepted_date());
        existingOffer.setState(offer.getState());
        existingOffer.setPhone(offer.getPhone());
        existingOffer.setEmail(offer.getEmail());
        existingOffer.setPassword(offer.getPassword());

        return offerRepository.save(existingOffer);
    }
}
