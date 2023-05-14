package com.raisetech.crudtask.application.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CoForm {

    @NotNull
    private LocalDate createdDate;

    @NotBlank
    private String countryOfOrigin;

    @NotBlank
    private  String productName;

    @NotBlank
    private String degreeOfRoasting;

    @NotBlank
    private String thoughts;
}
