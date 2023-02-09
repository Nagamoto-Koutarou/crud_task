package com.raisetech.crudTask.application.controller;

import com.raisetech.crudTask.infrastructure.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class CoResponse {

    private int id;
    private Date recordDay;
    private String countryOfOrigin;
    private String productName;
    private String degreeOfRoasting;
    private String thoughts;

    public CoResponse(Coffee coffee) {
        this.id = coffee.getId();
        this.recordDay = coffee.getRecordDay();
        this.countryOfOrigin = coffee.getCountryOfOrigin();
        this.productName = coffee.getProductName();
        this.degreeOfRoasting = coffee.getDegreeOfRoasting();
        this.thoughts = coffee.getThoughts();
    }
}
