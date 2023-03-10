package com.raisetech.crudTask.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Coffee {
    private int id;
    private LocalDate created_date;
    private String countryOfOrigin;
    private String productName;
    private String degreeOfRoasting;
    private String thoughts;
}
