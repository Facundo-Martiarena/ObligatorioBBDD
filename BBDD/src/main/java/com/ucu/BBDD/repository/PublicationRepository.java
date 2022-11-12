package com.ucu.BBDD.repository;

import com.ucu.BBDD.entity.Publication;
import com.ucu.BBDD.entity.PublicationPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepository extends JpaRepository<Publication, PublicationPK> {
    Publication findByPublicationPK(PublicationPK publicationPK);
}
