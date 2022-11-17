package com.ucu.BBDD.repository;

import com.ucu.BBDD.entity.Publication;
import com.ucu.BBDD.entity.PublicationUserFigureFK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepository extends JpaRepository<Publication, PublicationUserFigureFK> {
    Publication findByPublicationUserFigureFK(PublicationUserFigureFK publicationUserFigureFK);
}
