package com.raisetech.crudTask.application.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CoForm {

    @NotBlank
    private String countryOfOrigin;

    @NotBlank
    private  String productName;

    @NotBlank
    private String degreeOfRoasting;

    @NotBlank
    private String thoughts;
}
