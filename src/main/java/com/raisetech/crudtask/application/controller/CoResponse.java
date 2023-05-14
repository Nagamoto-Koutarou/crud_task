package com.raisetech.crudtask.application.controller;

import com.raisetech.crudtask.infrastructure.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class CoResponse {

    private int id;
    private LocalDate createdDate;
    private String countryOfOrigin;
    private String productName;
    private String degreeOfRoasting;
    private String thoughts;

    public CoResponse(Coffee coffee) {
        this.id = coffee.getId();
        this.createdDate = coffee.getCreatedDate();
        this.countryOfOrigin = coffee.getCountryOfOrigin();
        this.productName = coffee.getProductName();
        this.degreeOfRoasting = coffee.getDegreeOfRoasting();
        this.thoughts = coffee.getThoughts();
    }
}
