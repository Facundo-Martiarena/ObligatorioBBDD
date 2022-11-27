package com.ucu.BBDD.service;

import com.ucu.BBDD.entity.*;
import com.ucu.BBDD.repository.FigureRepository;
import com.ucu.BBDD.repository.FigureUserOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FigureUserOfferService {
    @Autowired
    private FigureUserOfferRepository figureUserOfferRepository;



//    public FigureUserOffer updateFigureUserOffer(FigureUserOffer figureUserOffer){
//        FigureUserOffer existingFigureUserOffer = figureUserOfferRepository.findById(figureUserOffer.getFigureUserOfferPK()).orElse(null);
//
//        return figureUserOfferRepository.save(existingFigureUserOffer);
//    }
}
