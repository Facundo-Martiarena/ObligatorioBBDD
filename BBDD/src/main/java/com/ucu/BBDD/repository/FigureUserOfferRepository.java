package com.ucu.BBDD.repository;

import com.ucu.BBDD.entity.FigureUserOffer;
import com.ucu.BBDD.entity.FigureUserOfferPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FigureUserOfferRepository extends JpaRepository<FigureUserOffer, FigureUserOfferPK> {
    FigureUserOffer findByFigureUserOfferPK(FigureUserOfferPK figureUserOfferPK);
}
