package com.raisetech.crudTask.application.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class CoForm {

    private LocalDate created_date;

    @NotBlank
    private String countryOfOrigin;

    @NotBlank
    private  String productName;

    @NotBlank
    private String degreeOfRoasting;

    @NotBlank
    private String thoughts;
}
