package com.raisetech.crudTask.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Coffee {
    private int id;
    private Date recordDay;
    private String countryOfOrigin;
    private String productName;
    private String degreeOfRoasting;
    private String thoughts;
}
