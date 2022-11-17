package com.ucu.BBDD.controller;

import com.ucu.BBDD.entity.FigureUserOffer;
import com.ucu.BBDD.entity.Offer;
import com.ucu.BBDD.model.OfferResponseDTO;
import com.ucu.BBDD.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OfferController {

    @Autowired
    private OfferService offerService;

    @PostMapping("/addOffer")
    public Offer addOffer(@RequestBody Offer offer){
        return offerService.saveOffer(offer);
    }


    @GetMapping("/offers/{email}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public List<OfferResponseDTO> findAllOffers(@PathVariable String email){
        return offerService.getOffersUserMe(email);
    }

    @DeleteMapping("/deleteOffer/{id}")
    public String deleteOffer(@PathVariable String id){
        return offerService.deleteOffer(id);
    }


//    @PutMapping("/updateOffer")
//    public Offer updateOffer(@RequestBody Offer offer){
//        return offerService.updateOffer(offer);
//    }
}
