package com.ucu.BBDD.controller;

import com.ucu.BBDD.entity.FigureUserOffer;
import com.ucu.BBDD.entity.Offer;
import com.ucu.BBDD.model.*;
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

//    @PostMapping("/addOffer")
//    public Offer addOffer(@RequestBody RequestBodyOffer requestBodyOffer){
//        return offerService.saveOffer(requestBodyOffer.getEmail(), requestBodyOffer.getPublication_id());
//    }

    @PostMapping("/addOffer")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public OkResponseDTO addOffer(@RequestBody CreateOfferRequestDTO createOfferRequestDTO){
        boolean result = offerService.saveOffer(createOfferRequestDTO);
        return new OkResponseDTO(result);
    }


    @GetMapping("/offersBidder/{email}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public OfferResponseList findAllOffersBidder(@PathVariable String email){
        return offerService.getOffersBidder(email);
    }

    @GetMapping("/offersPublication/{email}/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public OffersFromPublication findAllOffersFromPublication(@PathVariable String email, @PathVariable String id){
        return offerService.getOffersFromPublication(email, id);
    }

    @PutMapping("/acceptOffer/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public OkResponseDTO acceptOffer( @PathVariable String id){
        boolean accept = offerService.acceptOffer( id);
        return new OkResponseDTO(accept);
    }

    @PutMapping("/rejectOffer/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public OkResponseDTO rejectOffer( @PathVariable String id){
        boolean accept = offerService.rejectOffer( id);
        return new OkResponseDTO(accept);
    }




    @DeleteMapping("/deleteOffer/{id}")
    public String deleteOffer(@PathVariable String id){
        return offerService.deleteOffer(id);
    }


}
