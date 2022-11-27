package com.ucu.BBDD.controller;

import com.ucu.BBDD.entity.Publication;
import com.ucu.BBDD.model.*;
import com.ucu.BBDD.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @GetMapping("/publications/{email}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public ResponsePublication findAllPublications(@PathVariable("email") String email){
        return publicationService.getPublications(email);
    }

    @GetMapping("/mypublications/{email}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public ResponsePublication findAllMyPublication(@PathVariable("email") String email){
        return publicationService.getPublicationsUserMe(email);
    }

    @PostMapping("/addPublication")
    public Publication addPublication(@RequestBody RequestAddPublicationDTO requestAddPublicationDTO){
        return publicationService.createPublication(requestAddPublicationDTO);
    }


    @PostMapping("/activate")
    public OkResponseDTO activatePublication(@RequestBody RequestBodyPublication requestBodyPublication) {
        Boolean res = publicationService.updateActivatedPublication(requestBodyPublication.getPublication_id(), requestBodyPublication.getActivate());
        return new OkResponseDTO(res);
    }
}
