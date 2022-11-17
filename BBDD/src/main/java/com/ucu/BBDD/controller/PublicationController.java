package com.ucu.BBDD.controller;

import com.ucu.BBDD.entity.Publication;
import com.ucu.BBDD.model.OkResponseDTO;
import com.ucu.BBDD.model.PublicationResponseDTO;
import com.ucu.BBDD.model.RequestBodyPublication;
import com.ucu.BBDD.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @GetMapping("/publications/{email}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public OkResponseDTO findAllPublications(@PathVariable("email") String email){
        Boolean resp = publicationService.getPublications(email);
        return new OkResponseDTO(resp);


    }

    @GetMapping("/mypublications/{email}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public OkResponseDTO findAllMyPublication(@PathVariable("email") String email){
        Boolean resp = publicationService.getPublicationsUserMe(email);
        return new OkResponseDTO(resp);
    }

    @PostMapping("/addPublication")
    public Publication addPublication(@RequestBody String id){
        return publicationService.savePublication(id);
    }


    @PostMapping("/activate")
    public Boolean activatePublication(@RequestBody RequestBodyPublication requestBodyPublication) {
        return publicationService.updateActivatedPublication(requestBodyPublication.getPublication_id(), requestBodyPublication.getActivate());
    }



}
