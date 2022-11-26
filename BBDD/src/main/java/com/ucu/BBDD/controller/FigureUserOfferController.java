package com.ucu.BBDD.controller;

import com.ucu.BBDD.entity.Figure;
import com.ucu.BBDD.entity.FigureUserOffer;
import com.ucu.BBDD.entity.FigureUserOfferPK;
import com.ucu.BBDD.service.FigureUserOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FigureUserOfferController {

    @Autowired
    private FigureUserOfferService figureUserOfferService;

//    @PostMapping("/addFigureUserOffer")
//    public FigureUserOffer addFigureUserOffer(@RequestBody FigureUserOffer figureUserOffer){
//        return figureUserOfferService.saveFigureUserOffer(figureUserOffer);
//    }
//
//
//    @GetMapping("/figuresUsersOffers")
//    public List<FigureUserOffer> findAllFiguresUserOffers(){
//        return figureUserOfferService.getFiguresUserOffers();
//    }
//
//    @DeleteMapping("/deleteFUO/{figurePK}")
//    public String deleteFigureUserOffer(@PathVariable FigureUserOfferPK figureUserOfferPK){
//        return figureUserOfferService.deleteFigureUserOffer(figureUserOfferPK);
//    }
//
//    @PutMapping("/updateFigureUser")
//    public FigureUserOffer updateFigureUserOffer(@RequestBody FigureUserOffer figureUserOffer){
//        return figureUserOfferService.updateFigureUserOffer(figureUserOffer);
//    }
}
