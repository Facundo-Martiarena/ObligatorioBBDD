package com.ucu.BBDD.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class DescriptionPublisherDTO {

    private List<String> description_publisher;

    public DescriptionPublisherDTO(List<String> description_publisher) {
        this.description_publisher = description_publisher;
    }

    public DescriptionPublisherDTO() {
    }
}
