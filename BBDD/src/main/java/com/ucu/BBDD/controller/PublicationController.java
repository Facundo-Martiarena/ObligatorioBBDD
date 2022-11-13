package com.ucu.BBDD.controller;

import com.ucu.BBDD.entity.Offer;
import com.ucu.BBDD.entity.Publication;
import com.ucu.BBDD.entity.PublicationPK;
import com.ucu.BBDD.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @PostMapping("/addPublication")
    public Publication addPublication(@RequestBody Publication publication){
        return publicationService.savePublication(publication);
    }


    @GetMapping("/publications")
    public List<Publication> findAllPublication(){
        return publicationService.getPublication();
    }

    @DeleteMapping("/deletePublication/{publicationPK}")
    public String deletePublication(@PathVariable PublicationPK publicationPK){
        return publicationService.deletePublication(publicationPK);
    }

    @PutMapping("/updatePublication")
    public Publication updatePublication(@RequestBody Publication publication){
        return publicationService.updatePublication(publication);
    }
}
