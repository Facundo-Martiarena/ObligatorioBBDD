package com.ucu.BBDD.model;

public class RequestBodyPublication {

    private Integer publication_id;
    private Boolean activate;

    public Integer getPublication_id() {
        return publication_id;
    }

    public void setPublication_id(Integer publication_id) {
        this.publication_id = publication_id;
    }

    public Boolean getActivate() {
        return activate;
    }

    public void setActivate(Boolean activate) {
        this.activate = activate;
    }

    public RequestBodyPublication(Integer publication_id, Boolean activate) {
        this.publication_id = publication_id;
        this.activate = activate;
    }

    public RequestBodyPublication() {
    }
}
