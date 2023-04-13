package com.raisetech.crudTask.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Coffee {
    private int id;
    private LocalDate created_date;
    private String countryOfOrigin;
    private String productName;
    private String degreeOfRoasting;
    private String thoughts;
}
